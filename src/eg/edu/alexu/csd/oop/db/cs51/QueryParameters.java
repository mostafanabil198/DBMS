package eg.edu.alexu.csd.oop.db.cs51;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;


/**
 * @author Muhammad Salah
 * the parameters object to be returned from the interpreters
 * holds the parameters of the query
 */
public class QueryParameters {
    private static final String DB_NAME = "DB_NAME";
    private static final String TABLE_NAME = "TABEL_NAME";
    private static final String COL_NAME = "COL";
    private static final String COL_TYPE = "TYPE";
    private static final String COL_VALUE = "VALUE";
    private static final String CONDITION = "CONDITION";
    
    /**
     * the map that holds the queries
     */
    private Map<String, String> param;
    /**
     * column number
     */
    private int colNum;
    
    public QueryParameters() {
        param = new HashMap<String , String>();
        colNum = 0;
    }
    /****************** setters*******************/
    
    public void setDatabaseName(String DBName) {
        param.put(this.DB_NAME, DBName);
    }
    
    public void setTableName(String tableName) {
        param.put(this.TABLE_NAME, tableName);
    }
    
    /**
     * insert a column in the creation of the table
     * 
     * @param colName 
     * @param colType
     */
    public void addColumnType(String colName, String colType) {
        colNum++;
        param.put(this.COL_NAME + colNum, colName);
        param.put(this.COL_TYPE + colNum, colType);
    }
    
    /**
     * insert and update
     * in the form of (id = 6, name = "ahmed")
     * 
     * @param columnName
     * @param value
     */
    public void addColumnValue(String columnName, String value) {
        colNum++;
        param.put(this.COL_NAME + colNum, columnName);
        param.put(this.COL_VALUE + colNum, value);
    }
    
    /**
     * insert
     * in the form of (5, "ahmed")
     * 
     * @param value
     */
    public void addColumnValue(String value) {
        colNum++;
        param.put(this.COL_NAME + colNum, String.valueOf(colNum));
        param.put(this.COL_VALUE + colNum, value);
    }
    
    /**
     * for the select
     * 
     * @param columnName
     */
    public void addColumnName(String columnName) {
        colNum++;
        param.put(this.COL_NAME + colNum, columnName);
    }
    
    public void setCondition(String condition) {
        param.put(this.CONDITION, condition);
    }
    
    /***************getters***************/
    
    /**
     * @return list of pairs containing column name and datatype pairs
     */
    public List<Pair<String, String>> getColumnsType(){
        List<Pair<String, String>> columns = new ArrayList<Pair<String, String>>();
        for(int i = 1; i <= colNum; i++) {
            columns.add(new Pair(param.get(this.COL_NAME + i), param.get(this.COL_TYPE + i)));
        }
        
        return columns;
    }
    
    /**
     * @return list of pairs containing the column name and value pairs
     */
    public List<Pair<String, String>> getColumnsValue(){
        List<Pair<String, String>> columns = new ArrayList<Pair<String, String>>();
        for(int i = 1; i <= colNum; i++) {
            columns.add(new Pair(param.get(COL_NAME + i), param.get(COL_VALUE + i)));
        }
        
        return columns;
    }
    
    /**
     * @return list of column names
     */
    public List<String> getColumnsName(){
        List<String> columns = new ArrayList<String>();
        for(int i = 1; i <= colNum; i++) {
            columns.add(param.get(COL_NAME + i));
        }
        
        return columns;
    }
    
    public String getCondition() {
        if(!param.containsKey(this.CONDITION)) {
            return "*";
        }
        return param.get(this.CONDITION);
    }
    
    public String getDatabaseName() {
        return param.get(this.DB_NAME);
    }
    
    public String getTableName() {
        return param.get(this.TABLE_NAME);
    }
}
