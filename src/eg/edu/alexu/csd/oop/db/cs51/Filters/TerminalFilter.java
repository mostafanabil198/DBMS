package eg.edu.alexu.csd.oop.db.cs51.Filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TerminalFilter implements Filter{
    private String colName;
    private String value;
    
    public TerminalFilter(String colName, String value) {
        this.colName = colName;
        this.value = value;
    }
    
    @Override
    public List<Map<String, String>> filterTable(List<Map<String, String>> table) {
        List<Map<String, String>> filtered = new ArrayList<Map<String, String>>();
        for(Map<String, String> row : table) {
            if(row.get(colName).equals(value)) {
                filtered.add(row);
            }
        }
        return filtered;
    }

}
