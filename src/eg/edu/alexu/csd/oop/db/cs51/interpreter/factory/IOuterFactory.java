package eg.edu.alexu.csd.oop.db.cs51.interpreter.factory;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.Database;

public interface IOuterFactory {
	
	public Object getOutput (String query, Database dbms) throws SQLException;

}
