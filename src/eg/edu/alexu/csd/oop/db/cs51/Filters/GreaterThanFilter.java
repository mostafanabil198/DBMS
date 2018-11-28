package eg.edu.alexu.csd.oop.db.cs51.Filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GreaterThanFilter implements Filter {
	private String colName;
	private String value;

	public GreaterThanFilter(String colName, String value) {
		this.colName = colName;
		this.value = value;
	}

	@Override
	public List<Map<String, String>> filterTable(List<Map<String, String>> table) {
		List<Map<String, String>> filtered = new ArrayList<Map<String, String>>();

		for (Map<String, String> row : table) {
			try {
				if (Integer.parseInt(row.get(colName).toLowerCase()) > Integer.parseInt(value)) {
					filtered.add(row);
				}
			} catch (Exception e) {
				if (row.get(colName).toLowerCase().compareTo(value) > 0) {
					filtered.add(row);
				}
			}
		}
		return filtered;
	}

}
