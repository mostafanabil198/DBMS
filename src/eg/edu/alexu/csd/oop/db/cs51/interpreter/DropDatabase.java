package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;

public class DropDatabase implements Interpreter {

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
}
