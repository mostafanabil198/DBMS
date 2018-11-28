package eg.edu.alexu.csd.oop.db.cs51.Filters;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterFactory {
    private static final String TERMINAL_REGEX = "([A-Za-z_][A-Za-z0-9_]*) *= *(('[^']+')|(\"[^\"]+\")|(\\d+))";
    private String condition;
    
    public FilterFactory(String condition) {
        this.condition = condition;
    }
    
    public Filter getFilter() throws SQLException {
        Pattern pattern = Pattern.compile(TERMINAL_REGEX);
        Matcher matcher = pattern.matcher(condition);
        if(matcher.matches()) {
            String colName = matcher.group(1);
            String value;
            if(colName.charAt(0) == '\'' || colName.charAt(0) == '"') {
            	value= matcher.group(2).substring(1, matcher.group(2).length()-1);	
            } else {
            	value = matcher.group(2);
            }
            
            Filter filter = new TerminalFilter(colName, value);
            return filter;
        } else {
            throw new SQLException();
        }
    }
}
