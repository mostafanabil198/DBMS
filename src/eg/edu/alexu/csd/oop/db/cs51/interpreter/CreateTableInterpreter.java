package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;

public class CreateTableInterpreter implements Interpreter{
    private static final String REGEX = "CREATE TABLE ([a-zA-Z_][a-zA-Z0-9_]*) *\\( *(([a-zA-Z_][a-zA-Z0-9_]*) (int|string) *(, *([a-zA-Z_][a-zA-Z0-9_]*) (int|string))*) *\\) *;*";
    @Override
    public QueryParameters interpret(String query) throws SQLException {
        Pattern pattern = Pattern.compile(REGEX,Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(query);
        
        if(!m.matches()) {
            throw new SQLException();
        }
        QueryParameters qp = new QueryParameters();
        qp.setTableName(m.group(1));
        String param = m.group(2);
        addTableColumns(qp, param);
        return qp;
    }
    
    private void addTableColumns(QueryParameters qp, String param) {
        String[] params = param.split(",");
        for(String p: params) {
            String[] ps = p.trim().split(" ");
            qp.addColumnType(ps[0].trim(), ps[1].trim());
        }
    }

}
