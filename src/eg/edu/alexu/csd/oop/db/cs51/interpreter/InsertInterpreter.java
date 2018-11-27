package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.visitor.Visitor;

public class InsertInterpreter implements Interpreter{
    private static final String REGEX1 = "INSERT INTO ([a-zA-Z_][a-zA-Z0-9_]*) *\\( *(([a-zA-Z_][a-zA-Z0-9_]*) *(, *([a-zA-Z_][a-zA-Z0-9_]*))*) *\\) *"
            + "VALUES *\\( *((('[^']+')|(\\d+)|(\"[^\"]+\")) *(, *(('[^']+')|(\\d+)|(\"[^\"]+\")))*) *\\) *;*";
    private static final String REGEX2 = "INSERT INTO ([a-zA-Z_][a-zA-Z0-9_]*) *"
            + "VALUES *\\( *((('[^']+')|(\\d+)|(\"[^\"]+\")) *(, *(('[^']+')|(\\d+)|(\"[^\"]+\")))*) *\\) *;*";
    @Override
    public QueryParameters interpret(String query) throws SQLException {
        Pattern pattern1 = Pattern.compile(REGEX1, Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(query);
        Pattern pattern2 = Pattern.compile(REGEX2, Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(query);
        if(matcher1.matches()) {
            QueryParameters qp = new QueryParameters();
            qp.setTableName(matcher1.group(1));
            mergeValuesColumns(qp, addValues(matcher1.group(6)), addcolumns(matcher1.group(2)));
            return qp;
        } else if(matcher2.matches()) {
            QueryParameters qp = new QueryParameters();
            qp.setTableName(matcher2.group(1));
            setValues(qp, addValues(matcher2.group(2)));
            return qp;
        } else {
            throw new SQLException();
        }
    }

    private List<String> addValues(String param) {
        List<String> values = new ArrayList<String>();
        String regex = "(('[^']+')|(\\d+)|(\"[^\"]+\")) *(, *(('[^']+')|(\\d+)|(\"[^\"]+\")))";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(param);
        while(m.find()) {
            values.add(m.group(1));
            param = param.replaceFirst(m.group(1), "");
            param = param.replaceFirst(",", "");
            param = param.trim();
            m = p.matcher(param);
        }
        values.add(param);
        return values;
    }
    private List<String> addcolumns(String param) {
        List<String> columns = new ArrayList<String>();
        String regex = "([a-zA-Z_][a-zA-Z0-9_]*) *(, *([a-zA-Z_][a-zA-Z0-9_]*))";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(param);
        while(m.find()) {
            columns.add(m.group(1));
            param = param.replaceFirst(m.group(1), "");
            param = param.replaceFirst(",", "");
            param = param.trim();
            m = p.matcher(param);
        }
        columns.add(param);
        return columns;
    }
    private void mergeValuesColumns(QueryParameters qp, List<String> values, List<String> columns) throws SQLException {
        if(values.size() != columns.size()) throw new SQLException();
        for(int i = 0; i < values.size(); i++) {
            String v = values.get(i);
            if(v.matches("((\"[^\"]+\")|('[^']+'))")) {
                v = v.substring(1, v.length() - 1);
            }
            qp.addColumnValue(columns.get(i), v);
        }
    }
    private void setValues(QueryParameters qp, List<String> values) throws SQLException {
        for(int i = 0; i < values.size(); i++) {
            String v = values.get(i);
            if(v.matches("((\"[^\"]+\")|('[^']+'))")) {
                v = v.substring(1, v.length() - 1);
            }
            qp.addColumnValue(values.get(i));
        }
    }

    @Override
	public Object accept(Visitor visit, String query) throws SQLException {
		// TODO Auto-generated method stub
		return visit.visit(this, query);
	}
}
