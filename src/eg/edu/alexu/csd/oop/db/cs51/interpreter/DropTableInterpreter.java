package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;

public class DropTableInterpreter implements Interpreter {
	private static final String pattern = "DROP TABLE ([a-zA-Z_][a-zA-Z0-9_]*) *;*";
	private String tableName;

	@Override
	public QueryParameters interpret(String query) throws SQLException {
		// TODO Auto-generated method stub
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(query);
		 if (matcher.matches()) {
	          tableName = matcher.group(1);
	      }else {
	         throw new SQLException();
	      }
		 QueryParameters parameters = new QueryParameters();
		 parameters.setTableName(tableName);
		return parameters;
	}

}