package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.visitor.Visitor;

public class DropDatabaseInterpreter implements Interpreter {

	private static final String REGEX = "drop database ([A-Za-z_][A-Za-z0-9_]*) *;*";

	@Override
	public QueryParameters interpret(String query) throws SQLException {
		// TODO Auto-generated method stub

		Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(query);
		if (match.matches()) {
			QueryParameters queryParameters = new QueryParameters();
			queryParameters.setDatabaseName(match.group(1));
			return queryParameters;
		} else {

			throw new SQLException();
		}
	}

	@Override
	public Object accept(Visitor visit, String query) throws SQLException, ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		return visit.visit(this, query);
	}
}
