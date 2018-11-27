package eg.edu.alexu.csd.oop.db.cs51.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.db.cs51.filesParsers.SchemaReader;
import eg.edu.alexu.csd.oop.db.cs51.filesParsers.SchemaWriter;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class Schema {
    private List<Pair<String, String>> columnType;
    private String schemaFilePath;
    private String tableName;
    
    private Schema() {
        
    }
    
    public Schema(String tableName) {
    	//get path of database
        //load schema
    	this.tableName = tableName;
    	columnType = SchemaReader.readSchema(schemaFilePath);
    }

    
    public boolean validateColumnNames(List<String> columns) {
        if(columnType.size() < columns.size()) return false;
    	boolean validate;
    	for(int i = 0;i < columns.size(); i++){
    		validate = false;
    		for(int j = 0; j < columnType.size(); j++) {
    			if(columns.get(i).equals(columnType.get(j).getKey())) {
    				validate = true;
    				break;
    			}
    		}
    		if(!validate) {
    			return false;
    		}
    	}
        return true;
    }
    
    public Map<String, String> validateColumnValueType(List<Pair<String, String>> columnValue) {
        if(columnValue.size() > columnType.size()) return null;
        Map<String, String> valid = new HashMap<String, String>();
    	for(Pair<String, String> p : columnType) {
    	    String colName = p.getKey();
    	    String colType = p.getValue();
    	    boolean found = false;
    	    for (Pair<String, String> cv : columnValue) {
    	        String cName = cv.getKey();
    	        String colValue = cv.getValue();
    	        
    	        if(colName.equals(cName)) {
    	            if(colType.equals("int")) {
    	                try {
                            Integer.parseInt(colValue);
                        } catch (Exception e) {
                            return null;
                        }
    	            }
    	            found = true;
    	            valid.put(cName, colValue);
    	            columnValue.remove(cv);
    	            break;
    	        }
    	    }
    	    if(!found) {
    	        valid.put(colName, "NULL");
    	    }
    	}
    	if(columnValue.size() > 0) {
    	    return null;
    	} else {
    	    return valid;
    	}
    }
    
    public void save() {
      SchemaWriter.saveSchama(schemaFilePath, columnType, tableName);
    }
    public static Schema createNewSchema(String tableName, List<Pair<String, String>> columnType) {
    	Schema schema = new Schema();
    	schema.columnType = columnType;
    	schema.tableName = tableName;
    	//schema.schemaFilePath = database path + tableName + ".dtd";
        return schema;
    }

}
