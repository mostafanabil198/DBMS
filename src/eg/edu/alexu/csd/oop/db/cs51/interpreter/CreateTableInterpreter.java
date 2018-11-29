package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.visitor.Visitor;

public class CreateTableInterpreter implements Interpreter{
    private static final String REGEX = "create +table +([a-zA-Z_][a-zA-Z0-9_]*) *\\( *(([a-zA-Z_][a-zA-Z0-9_]*) +(int|varchar)( *, *([a-zA-Z_][a-zA-Z0-9_]*) +(int|varchar))*) *\\) *;*";
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

	@Override
	public Object accept(Visitor visit, String query) throws SQLException, ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		return visit.visit(this, query);
	}

}
