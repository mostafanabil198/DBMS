package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.visitor.Visitor;

public class UpdateInterpreter implements Interpreter {
	private static final String UPDATE_PATTERN1 = "update ([A-Za-z_][A-Za-z0-9_]*) set (([A-Za-z_][A-Za-z0-9_]*) *= *((\".+\")|('.+')|(\\d+))"
			+ " *(, *([A-Za-z_][A-Za-z0-9_]*) *= *((\".+\")|('.+')|(\\d+)))*) where ([^;]+) *;*";
	private static final String UPDATE_PATTERN2 = "update ([A-Za-z_][A-Za-z0-9_]*) set (([A-Za-z_][A-Za-z0-9_]*) *= *((\".+\")|('.+')|(\\d+))"
			+ " *(, *([A-Za-z_][A-Za-z0-9_]*) *= *((\".+\")|('.+')|(\\d+)))*)";
	private static final String GET_COLUMNS_PATTERN = "(([A-Za-z_][A-Za-z0-9_]*) *= *((\"[^\"]+\")|('[^']+')|(\\d+))(( *,)|(\\z)))";

	@Override
	public QueryParameters interpret(String query) throws SQLException {
		Pattern pattern1 = Pattern.compile(UPDATE_PATTERN1, Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(query);
		Pattern pattern2 = Pattern.compile(UPDATE_PATTERN2, Pattern.CASE_INSENSITIVE);
		Matcher matcher2 = pattern2.matcher(query);
		if (matcher1.matches()) {
			QueryParameters queryParameters = new QueryParameters();
			queryParameters.setTableName(matcher1.group(1));
			getColumnsAndValues(matcher1.group(2), queryParameters);
			queryParameters.setCondition(matcher1.group(14));
			return queryParameters;
		} else if (matcher2.matches()) {
			QueryParameters queryParameters = new QueryParameters();
			queryParameters.setTableName(matcher2.group(1));
			getColumnsAndValues(matcher2.group(2), queryParameters);
			return queryParameters;
		} else {
			throw new SQLException();
		}
	}

	private void getColumnsAndValues(String columns, QueryParameters queryParameters) {
		Pattern pattern = Pattern.compile(GET_COLUMNS_PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(columns);
		while (matcher.find()) {
			String name = matcher.group(2);
			String value = matcher.group(3);
			if (value.startsWith("\'") || value.startsWith("\"")) {
				value = value.substring(1, value.length() - 1);
			}
			queryParameters.addColumnValue(name, value);
		}

	}

	@Override
	public Object accept(Visitor visit, String query) throws SQLException {
		// TODO Auto-generated method stub
		return visit.visit(this, query);
	}

}
