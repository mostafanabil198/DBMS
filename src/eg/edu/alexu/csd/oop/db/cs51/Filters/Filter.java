package eg.edu.alexu.csd.oop.db.cs51.Filters;

import java.util.List;
import java.util.Map;

public interface Filter {
    public List<Map<String, String>> filterTable(List<Map<String, String>> table);
}
