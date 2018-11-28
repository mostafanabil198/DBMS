package eg.edu.alexu.csd.oop.db.cs51.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.Filters.Filter;
import eg.edu.alexu.csd.oop.db.cs51.Filters.FilterFactory;
import eg.edu.alexu.csd.oop.db.cs51.database.CurrentDatabase;
import eg.edu.alexu.csd.oop.db.cs51.database.Table;

public class DeleteCommand implements Command {
    @Override
    public Object execute(QueryParameters qp) throws ParserConfigurationException, SAXException, IOException, SQLException {
        String tableName = qp.getTableName();
        String condition = qp.getCondition();
        Table table = CurrentDatabase.getInstance().getTableFromCache(tableName);
        if(table == null) throw new SQLException();
        int deleted;
        if(condition.equals("*")) {
            deleted = table.delete();
        } else {
            FilterFactory filterFactory = new FilterFactory(condition);
            Filter filter = filterFactory.getFilter();
            deleted = table.delete(filter);
        }
        CurrentDatabase.getInstance().cacheTable(table);
        return deleted;
    }
}
