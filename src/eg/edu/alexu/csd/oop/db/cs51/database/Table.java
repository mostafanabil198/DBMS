package eg.edu.alexu.csd.oop.db.cs51.database;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		save();
		schema.save();
	}

	/**
	 * if the query like .. Insert into t_n (col,col,col) (val1, val2, val3)
	 * 
	 * @param columnValue
	 * @return
	 * @throws SQLException
	 */
	public int insert(List<Pair<String, String>> columnValue) throws SQLException {
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
	public void drop() {
		File schemaDtd = new File(databaseName + System.getProperty("file.separator") + tableName + ".dtd");
		if (tablePath.exists()) {
			tablePath.delete();
			schemaDtd.delete();
		}
	}

	/**
	 * if delete with condition
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException 
	 */
	public int delete(Filter condition) throws SQLException {
		List<Map<String, String>> filteredTable = condition.filterTable(tableRows);
		for (Map<String, String> deletedRow : filteredTable) {
			tableRows.remove(deletedRow);
		}
		return filteredTable.size();
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
//			for (String k : updateRow.keySet()) {
//				if (updateRow.get(k) == "NULL") {
//					updateRow.remove(k);
//				}
//			}
			Set<String> keySet = updateRow.keySet();
			String[] keySetArr = new String[keySet.size()];
			int i = 0;
			for(String s : keySet) {
				keySetArr[i] = s;
				i++;
			}
			for (i = 0; i < keySetArr.length; i++) {
				if(updateRow.get(keySetArr[i]) == "NULL") {
					updateRow.remove(keySetArr[i]);
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
	 * @throws SQLException
	 */
	public int update(List<Pair<String, String>> columnValue, Filter condition) throws SQLException {
		Map<String, String> updateRow = schema.validateColumnValueType(columnValue);
		if (updateRow == null) {
			throw new SQLException();
		} else {
//			for (String k : updateRow.keySet()) {
//				if (updateRow.get(k) == "NULL") {
//					updateRow.remove(k);
//				}
//			}
			Set<String> keySet = updateRow.keySet();
			String[] keySetArr = new String[keySet.size()];
			int i = 0;
			for(String s : keySet) {
				keySetArr[i] = s;
				i++;
			}
			for (i = 0; i < keySetArr.length; i++) {
				if(updateRow.get(keySetArr[i]) == "NULL") {
					updateRow.remove(keySetArr[i]);
				}
			}
			List<Map<String, String>> filteredTable = condition.filterTable(tableRows);
			for (Map<String, String> row : filteredTable) {
				for (String k : updateRow.keySet()) {
					row.put(k, updateRow.get(k));
				}
			}

			return filteredTable.size();
		}
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
			List<Map<String, Object>> newTableRows = schema.parseTypesOf(tableRows);
			List<String> columnsOrder = schema.getColumns();
			if (columns.size() == 1 && columns.get(0).equals("*")) {
				if (!newTableRows.isEmpty()) {

					Object[][] allTable = new Object[newTableRows.size()][newTableRows.get(0).size()];
					for (int i = 0; i < newTableRows.size(); i++) {

						for (int j = 0; j < columnsOrder.size(); j++) {
							allTable[i][j] = newTableRows.get(i).get(columnsOrder.get(j));
						}
					}
					return allTable;
				} else {
					return new Object[0][0];
				}

			}

			else {
				Object[][] allTable = new Object[newTableRows.size()][columns.size()];
				if (!newTableRows.isEmpty()) {
					for (int i = 0; i < newTableRows.size(); i++) {
						for (int j = 0; j < columns.size(); j++) {

							allTable[i][j] = newTableRows.get(i).get(columns.get(j));
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
	 * @throws SQLException 
	 */
	public Object[][] select(List<String> columns, Filter condition) throws SQLException {
		List<Map<String, String>> filteredTable = condition.filterTable(tableRows);
		List<Map<String, Object>> newFilteredTable = schema.parseTypesOf(filteredTable);
		List<String> columnsOrder = schema.getColumns();
		Object[][] selectedRows;
		if (columns.size() == 1 && columns.get(0).equals("*")) {
			if (newFilteredTable.isEmpty()) {
				return selectedRows = new Object[0][0];
			} else {
				selectedRows = new Object[newFilteredTable.size()][newFilteredTable.get(0).size()];
			}
			int i = 0;
			for (Map<String, Object> selectedRow : newFilteredTable) {
				int j = 0;
				for (String key : columnsOrder) {
					selectedRows[i][j] = selectedRow.get(key);
					j++;
				}
				i++;
			}
			return selectedRows;
		} else {
			selectedRows = new Object[newFilteredTable.size()][columns.size()];
			if (!newFilteredTable.isEmpty()) {
				for (int i = 0; i < newFilteredTable.size(); i++) {
					for (int j = 0; j < columns.size(); j++) {

						selectedRows[i][j] = newFilteredTable.get(i).get(columns.get(j));
					}
				}
				return selectedRows;
			} else {
				return selectedRows = new Object[0][0];

			}

		}

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
