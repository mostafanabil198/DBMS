package eg.edu.alexu.csd.oop.db.cs51.database;

import java.util.List;

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
    
    public boolean validateColumnValueType(List<Pair<String, String>> columnValue) {
    	boolean validate;
    	for(int i = 0 ;i< columnValue.size(); i++) {
    		validate = false;
    		for(int j = 0;j< columnType.size();j++) {
    			if(columnValue.get(i).getKey() == columnType.get(j).getKey()) {
    				validate = true;
    				if(columnType.get(j).getValue().equals("int")) {
    					try {
							int num = Integer.parseInt(columnValue.get(i).getValue());
						} catch (Exception e) {
							// TODO: handle exception
							return false;
						}
    				} 
    				break;
    			} 
    			
    		} 
    		if(!validate) {
    			return false;
    		}
    	}
        return true;
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
