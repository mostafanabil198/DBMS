package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;

public interface Interpreter {
	public QueryParameters interpret(String query);
}
