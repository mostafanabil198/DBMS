package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;

public interface Interpreter {
	/**
	 * @param query string query to parse
	 * @return the parameters of the query 
	 */
	public QueryParameters interpret(String query)  throws SQLException;
}
