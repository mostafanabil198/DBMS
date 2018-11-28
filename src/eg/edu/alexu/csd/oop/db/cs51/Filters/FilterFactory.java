package eg.edu.alexu.csd.oop.db.cs51.Filters;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterFactory {
	private static final String EQUAL_REGEX = "([A-Za-z_][A-Za-z0-9_]*) *= *(('[^']+')|(\"[^\"]+\")|(\\d+))";
	private static final String LESS_THAN_REGEX = "([A-Za-z_][A-Za-z0-9_]*) *< *(('[^']+')|(\"[^\"]+\")|(\\d+))";
	private static final String GREATER_THAN_REGEX = "([A-Za-z_][A-Za-z0-9_]*) *> *(('[^']+')|(\"[^\"]+\")|(\\d+))";
	private String condition;

	public FilterFactory(String condition) {
		this.condition = condition.toLowerCase();
	}

	public Filter getFilter() throws SQLException {
		Pattern pattern = Pattern.compile(EQUAL_REGEX);
		Pattern pattern2 = Pattern.compile(LESS_THAN_REGEX);
		Pattern pattern3 = Pattern.compile(GREATER_THAN_REGEX);
		Matcher matcher = pattern.matcher(condition);
		Matcher matcher2 = pattern2.matcher(condition);
		Matcher matcher3 = pattern3.matcher(condition);
		if (matcher.matches()) {
			String colName = matcher.group(1);
			String value = matcher.group(2);
			if (value.matches("((\"[^\"]+\")|('[^']+'))")) {
				value = value.substring(1, value.length() - 1);
			} else {
				value = matcher.group(2);
			}
			Filter filter = new EqualFilter(colName, value);
			return filter;
		} else if (matcher2.matches()) {
			String colName = matcher2.group(1);
			String value = matcher2.group(2);
			if (value.matches("((\"[^\"]+\")|('[^']+'))")) {
				value = value.substring(1, value.length() - 1);
			} else {
				value = matcher2.group(2);
			}
			Filter filter = new LessThanFilter(colName, value);
			return filter;
		} else if (matcher3.matches()) {
			String colName = matcher3.group(1);
			String value = matcher3.group(2);
			if (value.matches("((\"[^\"]+\")|('[^']+'))")) {
				value = value.substring(1, value.length() - 1);
			} else {
				value = matcher3.group(2);
			}
			Filter filter = new GreaterThanFilter(colName, value);
			return filter;
		} else {
			throw new SQLException();
		}
	}
}
