package eg.edu.alexu.csd.oop.db.cs51.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.database.CurrentDatabase;
import eg.edu.alexu.csd.oop.db.cs51.database.Table;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class InsertCommand implements Command{
    @Override
    public Object execute(QueryParameters qp) throws ParserConfigurationException, SAXException, IOException, SQLException {
		if (CurrentDatabase.getInstance().getPath() != null) {

    	String tableName = qp.getTableName();
        List<Pair<String, String>> columnValue = qp.getColumnsValue();
        Table table = CurrentDatabase.getInstance().getTableFromCache(tableName);
        if(table == null) throw new SQLException();
        int inserted = table.insert(columnValue);
        CurrentDatabase.getInstance().cacheTable(table);
        return inserted;
		} else {
			throw new SQLException();

		}
    }
}
