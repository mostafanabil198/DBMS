package eg.edu.alexu.csd.oop.db.cs51.database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CurrentDatabase {
    private static volatile CurrentDatabase obj;
    private String databasePath;
    private List<String> TableNames;
    
    private CurrentDatabase() {}
    
    public static CurrentDatabase getInstance() {
        if(obj == null) {
            synchronized (CurrentDatabase.class) {
                if(obj == null) {
                    obj = new CurrentDatabase();
                }
            }
        }
        
        return obj;
    }

    public void createDatabase(String path, boolean dropIfExist) {
        this.databasePath = path;
        File file = new File(databasePath);
        if(file.exists()) {
            if(dropIfExist) {
                for(String dir : file.list()) {
                    File f = new File(dir);
                    f.delete();
                }
            } else {
                TableNames = new ArrayList<String>();
                for(String dir : file.list()) {
                    if(dir.endsWith(".dtd")) {
                        dir = dir.substring(0, dir.length() - 4);
                        
                    }
                }
            }
        } else {
            file.mkdir();
        }
        
    }
    
    public String getPath() {
        return this.databasePath;
    }
}
