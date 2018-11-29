package eg.edu.alexu.csd.oop.db.cs51.Filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotFilter implements Filter{
    private Filter a;

    
    public NotFilter(Filter a) {
        this.a = a;
    }
    @Override
    public List<Map<String, String>> filterTable(List<Map<String, String>> table) {
        List<Map<String, String>> filtered = a.filterTable(table);
        List<Map<String, String>> Notfilter = new ArrayList<Map<String, String>>();
        for(Map<String, String> row: table) {
            if(!filtered.contains(row)) {
                Notfilter.add(row);
            }
        }
        return Notfilter;
    }

}
