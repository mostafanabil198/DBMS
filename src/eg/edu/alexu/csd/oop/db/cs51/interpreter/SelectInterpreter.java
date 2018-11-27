package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.visitor.Visitor;

public class SelectInterpreter implements Interpreter {
	private static final String SELECT_PATTERN1 = "select (([A-Za-z_][A-Za-z0-9_]*)( *, *[A-Za-z_][A-Za-z0-9_]*)*) from ([A-Za-z_][A-Za-z0-9_]*) *;*";
	private static final String SELECT_PATTERN2 = "select (\\*) from ([A-Za-z_][A-Za-z0-9_]*) *;*";
	private static final String SELECT_PATTERN3 = "select (([A-Za-z_][A-Za-z0-9_]*)( *, *[A-Za-z_][A-Za-z0-9_]*)*) from ([A-Za-z_][A-Za-z0-9_]*) where ([^;]+) *;*";
	private static final String GET_COLUMNS_PATTERN = "([A-Za-z_][A-Za-z0-9_]*)(( *,)|(\\z))";

	@Override
	public QueryParameters interpret(String query) throws SQLException {
		Pattern pattern1 = Pattern.compile(SELECT_PATTERN1, Pattern.CASE_INSENSITIVE);
		Pattern pattern2 = Pattern.compile(SELECT_PATTERN2, Pattern.CASE_INSENSITIVE);
		Pattern pattern3 = Pattern.compile(SELECT_PATTERN3, Pattern.CASE_INSENSITIVE);

		Matcher matcher1 = pattern1.matcher(query);
		Matcher matcher2 = pattern2.matcher(query);
		Matcher matcher3 = pattern3.matcher(query);
		QueryParameters queryParameters = new QueryParameters();
		if (matcher1.matches()) {
			queryParameters.setTableName(matcher1.group(4));
			getColumnsNames(matcher1.group(1), queryParameters);
			return queryParameters;
		} else if (matcher2.matches()) {
			queryParameters.setTableName(matcher2.group(2));
			queryParameters.addColumnName(matcher2.group(1));
			return queryParameters;

		} else if (matcher3.matches()) {
			queryParameters.setTableName(matcher3.group(4));
			queryParameters.setCondition(matcher3.group(5));
			getColumnsNames(matcher3.group(1), queryParameters);
			return queryParameters;
		} else {
			throw new SQLException();
		}

		
	}

	private void getColumnsNames(String columns, QueryParameters queryParameters) {
		columns = columns.trim();
		Pattern pattern = Pattern.compile(GET_COLUMNS_PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(columns);
		while (match.find()) {
			queryParameters.addColumnName(match.group(1));
		}
	}

	@Override
	public Object accept(Visitor visit, String query) throws SQLException {
		// TODO Auto-generated method stub
		return visit.visit(this, query);
	}

}
