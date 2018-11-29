package eg.edu.alexu.csd.oop.db.cs51.Filters;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterFactory {
	private static final String EQUAL_REGEX = "([A-Za-z_][A-Za-z0-9_]*) *= *(('[^']+')|(\"[^\"]+\")|(\\d+))";
	private static final String LESS_THAN_REGEX = "([A-Za-z_][A-Za-z0-9_]*) *< *(('[^']+')|(\"[^\"]+\")|(\\d+))";
	private static final String GREATER_THAN_REGEX = "([A-Za-z_][A-Za-z0-9_]*) *> *(('[^']+')|(\"[^\"]+\")|(\\d+))";
	private String condition;

	public FilterFactory(String condition) {
		this.condition = condition.toLowerCase().trim();
	}

	public Filter getFilter() throws SQLException{
        List<String> exp = parseExp();
        Stack<String> prefix = prefexExp(exp);
//        while(!prefix.isEmpty()) {
//            System.out.println(prefix.pop());
//        }
        return finalFilter(prefix);
    }
	
	private Filter getTerminalFilter(String exp) throws SQLException {
	    Pattern pattern = Pattern.compile(EQUAL_REGEX);
        Pattern pattern2 = Pattern.compile(LESS_THAN_REGEX);
        Pattern pattern3 = Pattern.compile(GREATER_THAN_REGEX);
        Matcher matcher = pattern.matcher(exp);
        Matcher matcher2 = pattern2.matcher(exp);
        Matcher matcher3 = pattern3.matcher(exp);
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
	
	private List<String> parseExp(){
	    String regex = "((\\()|(\\))|(and)|(or)|(not)|(([a-zA-Z_][a-zA-Z0-9_]* *(=|>|<) *(('[^']+')|(\\d+)|(\"[^\"]+\")))))";
	    Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(condition);
        List<String> exp = new ArrayList<String>();
        Stack<String> priorty = new Stack<String>();
        while(m.find()) {
            exp.add(m.group(1));
        }
        return exp;
	}
	
	private Stack<String> prefexExp(List<String> exp){
	    Stack<String> prefix = new Stack<String>();
	    Stack<String> postfix = new Stack<String>();
	    
	    while(!exp.isEmpty()) {
	        String part = exp.get(0);
	        exp.remove(0);
	        if(part.equals(")")) {
	            while(!postfix.peek().equals("(")) {
	                prefix.add(postfix.pop());
	            }
	            postfix.pop();
	        } else if (part.equals("(")) {
	            postfix.add(part);
	        } else if (part.matches("(not)|(and)|(or)")) {
	            if((!postfix.isEmpty()) && postfix.peek().matches("(not)|(and)|(or)")) {
	                prefix.add(postfix.pop());
	            }
	            postfix.add(part);
	        } else {
	            prefix.add(part);
	        }
	    }
	    
	    while(!postfix.isEmpty()) {
	        prefix.add(postfix.pop());
	    }
	    return prefix;
	}
	
	private Filter finalFilter(Stack<String> prefex) throws SQLException {
	    if(prefex.isEmpty()) {
	        return null;
	    }
	    if(prefex.peek().equals("and")) {
	        prefex.pop();
	        return new AndFilter(finalFilter(prefex), finalFilter(prefex));
	    } else if(prefex.peek().equals("or")) {
	        prefex.pop();
            return new OrFilter(finalFilter(prefex), finalFilter(prefex));
	    } else if(prefex.peek().equals("not")) {
	        prefex.pop();
            return new NotFilter(finalFilter(prefex));
	    } else {
	        String terminalexp = prefex.pop();
	        return getTerminalFilter(terminalexp);
	    }
	}
}
