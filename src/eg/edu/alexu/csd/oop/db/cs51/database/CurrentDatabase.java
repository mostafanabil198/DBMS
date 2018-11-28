package eg.edu.alexu.csd.oop.db.cs51.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class CurrentDatabase {
	private static volatile CurrentDatabase obj;
	private String databasePath;
	private List<String> tableNames;
	private Cache tablesCache;

	private CurrentDatabase() {}

	public static CurrentDatabase getInstance() {
		if (obj == null) {
			synchronized (CurrentDatabase.class) {
				if (obj == null) {
					obj = new CurrentDatabase();
				}
			}
		}

		return obj;
	}

	public boolean createDatabase(String path, boolean dropIfExist) {
	    if(this.tablesCache != null) {
	        this.tablesCache.shutdownCache();
	    }
	    tablesCache = new Cache();
		this.databasePath = path;
		tableNames = new ArrayList<String>();
		File file = new File(databasePath);
		if (file.exists()) {
			if (dropIfExist) {
				for (String dir : file.list()) {
					File f = new File(dir);
					f.delete();
				}
			} else {
				for (String dir : file.list()) {
					if (dir.endsWith(".dtd")) {
						dir = dir.substring(0, dir.length() - 4);
						tableNames.add(dir);
					}
				}
			}
		} else {
			file.mkdir();
		}
		return true;
	}

	public boolean dropDatabase(String path) {
	    File file = new File(path);
	    if(file.exists()) {
	        for (String dir : file.list()) {
                File f = new File(dir);
                f.delete();
            }
	        file.delete();
	        tablesCache = null;
	        obj = null;
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public boolean createNewTable(String tableName) {
	    if(tableNames.contains(tableName)) return false;
		tableNames.add(tableName);
		return true;
	}

	public Table getTableFromCache(String tableName) throws ParserConfigurationException, SAXException, IOException {
	    if(!tableNames.contains(tableName)) return null;
		return tablesCache.takeOut(tableName);
	}

	public void cacheTable(Table table) {
	    tablesCache.takeIn(table);
	}

	public String getPath() {
		return this.databasePath;
	}
	
	public boolean dropTable(String tableName) throws ParserConfigurationException, SAXException, IOException {
	    if(tableNames.contains(tableName)) {
	        Table table = this.tablesCache.removeFromCache(tableName);
	        tableNames.remove(tableName);
	        table.drop();
	        table = null;
	        return true;
	    }
	    return false;
	}
	
}
