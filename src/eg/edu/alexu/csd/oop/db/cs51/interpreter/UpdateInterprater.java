package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;

public class UpdateInterprater implements Interpreter {
	private static final String UPDATE_PATTERN1 = "update ([A-Za-z_][A-Za-z0-9_]*) set (([A-Za-z_][A-Za-z0-9_]*) *= *((\".+\")|('.+')|(\\d+))"
			+ " *(, *([A-Za-z_][A-Za-z0-9_]*) *= *((\".+\")|('.+')|(\\d+)))*) where (.+) *;*";
	private static final String UPDATE_PATTERN2 = "update ([A-Za-z_][A-Za-z0-9_]*) set (([A-Za-z_][A-Za-z0-9_]*) *= *((\".+\")|('.+')|(\\d+))"
			+ " *(, *([A-Za-z_][A-Za-z0-9_]*) *= *((\".+\")|('.+')|(\\d+)))*)";

	@Override
	public QueryParameters interpret(String query) throws SQLException {
		Pattern pattern1 = Pattern.compile(UPDATE_PATTERN1, Pattern.CASE_INSENSITIVE);
		Matcher matcher1 = pattern1.matcher(query);
		Pattern pattern2 = Pattern.compile(UPDATE_PATTERN2, Pattern.CASE_INSENSITIVE);
		Matcher matcher2 = pattern2.matcher(query);
		if (matcher1.matches()) {
			QueryParameters queryParameters = new QueryParameters();
			queryParameters.setTableName(matcher1.group(1));
			getColumnsAndValues(matcher1.group(2));
			queryParameters.setCondition(matcher1.group(8));
			return queryParameters;
		} else {
			throw new SQLException();
		}
	}
	
	private void getColumnsAndValues(String columns) {
		String regex = "(([A-Za-z_][A-Za-z0-9_]*) *= *((\".+\")|('.+')|(\\d+))(( *,)|(\\z)))";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(columns);
		int i = 0;
		while(matcher.find()) {
			i++;
			System.out.println(matcher.group(1));
		}
		
	}

}
