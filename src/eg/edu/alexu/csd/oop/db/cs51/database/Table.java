package eg.edu.alexu.csd.oop.db.cs51.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.Filters.Filter;
import eg.edu.alexu.csd.oop.db.cs51.filesParsers.TableLoader;
import eg.edu.alexu.csd.oop.db.cs51.filesParsers.TableWriter;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

/**
 * @author arabtech
 *
 */
public class Table {
	private List<Map<String, String>> tableRows;
	private Schema schema;
	private String tableName;
	private String databaseName;
	private File tablePath;

	/**
	 * Load table constructor
	 * 
	 * @param databaseName
	 * @param tableName
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public Table(String databaseName, String tableName) throws ParserConfigurationException, SAXException, IOException {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.tablePath = new File(databaseName + File.separator + tableName + ".xml");
		this.tableRows = loadTableRows(tablePath);
		schema = new Schema(tableName);
	}

	/**
	 * to create new table
	 * 
	 * @param databaseName
	 * @param tableName
	 * @param columnType
	 */
	private Table(String databaseName, String tableName, List<Pair<String, String>> columnType) {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.tablePath = new File(databaseName + File.separator + tableName + ".xml");
		schema = Schema.createNewSchema(tableName, columnType);
		this.tableRows = new ArrayList<>();
	}

	/**
	 * if the query like .. Insert into t_n (col,col,col) (val1, val2, val3)
	 * 
	 * @param columnValue
	 * @return
	 */
	public int insertUnOrdered(List<Pair<String, String>> columnValue) {
		return 1;
	}

	/**
	 * if the query like .. insert into t_n values (v1, v2, v3)
	 * 
	 * @param columnValue
	 * @return
	 */
	public int insertOrdered(List<Pair<String, String>> columnValue) {
		return 1;
	}

	/**
	 * drop table
	 * 
	 * @return
	 */
	public boolean drop() {
		return true;
	}

	/**
	 * if delete with condition
	 * 
	 * @param condition
	 * @return
	 */
	public int delete(Filter condition) {
		return 1;
	}

	/**
	 * delete all rows in table
	 * 
	 * @return
	 */
	public int delete() {
		tableRows.clear();
		return tableRows.size();
	}

	/**
	 * update all all rows with these values
	 * 
	 * @param columnValue
	 * @return
	 */
	public int update(List<Pair<String, String>> columnValue) {
		return 1;
	}

	/**
	 * update with condition
	 * 
	 * @param columnValue
	 * @param condition
	 * @return
	 */
	public int update(List<Pair<String, String>> columnValue, Filter condition) {
		return 1;
	}

	/**
	 * select all these columns
	 * 
	 * @param columns
	 * @return
	 */
	public Object[][] select(List<String> columns) {
		return null;

	}

	/**
	 * select with this condition
	 * 
	 * @param columns
	 * @param condition
	 * @return
	 */
	public Object[][] select(List<String> columns, Filter condition) {
		return null;

	}

	/**
	 * load table rows in the list
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	private List<Map<String, String>> loadTableRows(File path)
			throws ParserConfigurationException, SAXException, IOException {
		TableLoader tableLoader = new TableLoader();
		return tableLoader.load(path);
	}

	/**
	 * create new instance of Table
	 * 
	 * @param databaseName
	 * @param tableName
	 * @param columnType
	 * @return
	 */

	public void save() {
		TableWriter tableWriter = new TableWriter();
		tableWriter.saveTable(tablePath, tableRows);
	}

	public static Table createTable(String databaseName, String tableName, List<Pair<String, String>> columnType) {
		Table newTable = new Table(databaseName, tableName, columnType);
		return newTable;
	}

}
