package eg.edu.alexu.csd.oop.db.cs51.database;

import java.util.List;

import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class Schema {
    private List<Pair<String, String>> columnType;
    private String schemaFilePath;
    
    public Schema() {
        
    }
    
    public Schema(String tableName) {
        //load schema
    }

    
    public boolean validateColumnNames(List<String> columns) {
        return true;
    }
    
    public boolean validateColumnValueType(List<Pair<String, String>> columnValue) {
        return true;
    }
    public void save() {
        
    }
    public static Schema createNewSchema(String tableName, List<Pair<String, String>> columnType) {
        return new Schema();
    }

}
