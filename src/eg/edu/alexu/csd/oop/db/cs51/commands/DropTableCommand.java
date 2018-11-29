package eg.edu.alexu.csd.oop.db.cs51.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.database.CurrentDatabase;

public class DropTableCommand implements Command{

	@Override
	public Object execute(QueryParameters qp) throws ParserConfigurationException, SAXException, IOException, SQLException {
		// TODO Auto-generated method stub
		
		if (CurrentDatabase.getInstance().getPath() != null) {

		String tableName = qp.getTableName();
		return CurrentDatabase.getInstance().dropTable(tableName);
		} else {
			throw new SQLException();
		}
	}
	

}
