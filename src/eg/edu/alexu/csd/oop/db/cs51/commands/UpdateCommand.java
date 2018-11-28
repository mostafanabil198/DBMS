package eg.edu.alexu.csd.oop.db.cs51.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.Filters.Filter;
import eg.edu.alexu.csd.oop.db.cs51.Filters.FilterFactory;
import eg.edu.alexu.csd.oop.db.cs51.database.CurrentDatabase;
import eg.edu.alexu.csd.oop.db.cs51.database.Table;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class UpdateCommand implements Command{

	@Override
	public Object execute(QueryParameters qp) throws ParserConfigurationException, SAXException, IOException, SQLException {
		String tableName = qp.getTableName();
		String condition = qp.getCondition();
		int updated;
		List<Pair<String, String>> columnsValue = qp.getColumnsValue();
		Table t = CurrentDatabase.getInstance().getTableFromCache(tableName);
		if(t == null) {
			throw new SQLException() ;
		}
		if(condition.equals("*")) {
			updated = t.update(columnsValue);
		} else {
			Filter f = (Filter) new FilterFactory(condition).getFilter();
			updated = t.update(columnsValue, f);
		}
		CurrentDatabase.getInstance().cacheTable(t);
		return updated;
	}
	

}
