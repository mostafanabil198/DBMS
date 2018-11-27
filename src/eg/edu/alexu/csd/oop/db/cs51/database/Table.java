package eg.edu.alexu.csd.oop.db.cs51.database;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
	private Table(String tableName) throws ParserConfigurationException, SAXException, IOException {
		this.databaseName = CurrentDatabase.getInstance().getPath();
		this.tableName = tableName;
		this.tablePath = new File(databaseName + System.getProperty("file.separator") + tableName + ".xml");
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
	private Table(String tableName, List<Pair<String, String>> columnType) {
		this.databaseName = CurrentDatabase.getInstance().getPath();
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
	 * @throws SQLException
	 */
	public int insertUnOrdered(List<Pair<String, String>> columnValue) throws SQLException {
		Map<String, String> newRow = schema.validateColumnValueType(columnValue);
		if (newRow == null) {
			throw new SQLException();
		} else {
			tableRows.add(newRow);
			return 1;
		}

	}

	/**
	 * if the query like .. insert into t_n values (v1, v2, v3)
	 * 
	 * @param columnValue
	 * @return
	 */
	/*
	 * public int insertOrdered(List<Pair<String, String>> columnValue) {
	 * 
	 * return 1; }
	 */

	/**
	 * drop table
	 * 
	 * @return
	 */
	public boolean drop() {
		// --------------------- drop in db and cache ----------------------------/
		File schemaDtd = new File(databaseName + System.getProperty("file.separator") + tableName + ".dtd");
		if (tablePath.exists()) {
			tablePath.delete();
			schemaDtd.delete();
			return true;
		}
		return false;
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
		int size = tableRows.size();
		tableRows.clear();
		return size;
	}

	/**
	 * update all all rows with these values
	 * 
	 * @param columnValue
	 * @return
	 * @throws SQLException
	 */
	public int update(List<Pair<String, String>> columnValue) throws SQLException {
		Map<String, String> updateRow = schema.validateColumnValueType(columnValue);
		if (updateRow == null) {
			throw new SQLException();
		} else {
			for (String k : updateRow.keySet()) {
				if (updateRow.get(k) == "NULL") {
					updateRow.remove(k);
				}
			}
			for (Map<String, String> row : tableRows) {
				for (String k : updateRow.keySet()) {
					row.put(k, updateRow.get(k));
				}
			}
			return tableRows.size();
		}

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
	 * @throws SQLException
	 */
	public Object[][] select(List<String> columns) throws SQLException {
		if (schema.validateColumnNames(columns)) {

			List<String> columnsOrder = schema.getColumns();

			if (columns.size() == 1 && columns.get(0) == "*") {

				if (!tableRows.isEmpty()) {

					Object[][] allTable = new Object[tableRows.size()][tableRows.get(0).size()];
					for (int i = 0; i < tableRows.size(); i++) {

						for (int j = 0; j < columnsOrder.size(); j++) {

							try {
								allTable[i][j] = Integer.parseInt(tableRows.get(i).get(columnsOrder.get(j)));
							} catch (Exception e) {
								allTable[i][j] = tableRows.get(i).get(columnsOrder.get(j));
							}
						}
					}
					return allTable;
				} else {
					return new Object[0][0];
				}

			}

			else {
				Object[][] allTable = new Object[tableRows.size()][columns.size()];
				if (!tableRows.isEmpty()) {
					for (int i = 0; i < tableRows.size(); i++) {
						for (int j = 0; j < columns.size(); j++) {
							try {
								allTable[i][j] = Integer.parseInt(tableRows.get(i).get(columns.get(j)));
							} catch (Exception e) {
								allTable[i][j] = tableRows.get(i).get(columns.get(j));
							}
						}
					}
					return allTable;
				} else {
					return new Object[0][0];

				}

			}
		}

		else {
			return new Object[0][0];
		}

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

	public static Table createNewTable(String tableName, List<Pair<String, String>> columnType) {
		Table newTable = new Table(tableName, columnType);
		return newTable;
	}
	
	public static Table loadTable(String tableName) throws ParserConfigurationException, SAXException, IOException {
		Table newTable = new Table(tableName);
		return newTable;
	}
	
	public String getTableName() {
		return tableName;
	}

}
