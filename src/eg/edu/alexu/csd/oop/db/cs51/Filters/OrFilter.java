package eg.edu.alexu.csd.oop.db.cs51.Filters;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrFilter implements Filter{
    private Filter a;
    private Filter b;
    
    public OrFilter(Filter a, Filter b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public List<Map<String, String>> filterTable(List<Map<String, String>> table) throws SQLException {
       try {
    	List<Map<String, String>> filtered1 = a.filterTable(table);
        List<Map<String, String>> filtered2 = b.filterTable(table);
        for (Map<String, String> row: filtered1) {
            if(!filtered2.contains(row)) {
                filtered2.add(row);
            }
        }
        return filtered2;
       } catch(Exception e) {
   		throw new SQLException();
   	}
    }

}
