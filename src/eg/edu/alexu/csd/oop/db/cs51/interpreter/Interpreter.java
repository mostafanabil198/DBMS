package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.visitor.Visitor;

public interface Interpreter {
	/**
	 * @param query string query to parse
	 * @return the parameters of the query 
	 */
	public QueryParameters interpret(String query)  throws SQLException;
	public void accept(Visitor visit,String query) throws SQLException;
}
