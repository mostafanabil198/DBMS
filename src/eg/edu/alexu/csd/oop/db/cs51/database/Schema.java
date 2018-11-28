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
    	schemaFilePath = CurrentDatabase.getInstance().getPath() + System.getProperty("file.separator") + tableName + ".dtd";
        //load schema
    	this.tableName = tableName;
    	columnType = SchemaReader.readSchema(schemaFilePath);
    }

    public List<String> getColumns(){
        List<String> col = new ArrayList<String>();
        for(Pair<String, String> p : columnType) {
            col.add(p.getKey());
        }
        return col;
    }
    
    public boolean validateColumnNames(List<String> columns) {
        if(columnType.size() < columns.size()) return false;
        if(columns.size() == 1) {
            if(columns.get(0).equals("*")) return true;
        }
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
    	        try {
                    int colNum = Integer.parseInt(cName);
                    colNum--;
                    cName = columnType.get(colNum).getKey();
                } catch (Exception e) {
                }
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
    
    public List<Map<String, Object>> parseTypesOf(List<Map<String, String>> rows){
        List<Map<String, Object>> parsed = new ArrayList<Map<String, Object>>();
        for(Map<String, String> row : rows) {
            Map<String, Object> newRow = new HashMap<String, Object>();
            for(String colName: row.keySet()) {
                for(Pair colType: columnType) {
                    if(colType.getKey().equals(colName)) {
                        if(colType.getValue().equals("int")) {
                            try {
                                newRow.put(colName, Integer.parseInt(row.get(colName)));
                            } catch (Exception e) {
                            }
                        } else {
                            newRow.put(colName, row.get(colName));
                        }
                        break;
                    }
                }
            }
            parsed.add(newRow);
        }
        return parsed;
    }

}
