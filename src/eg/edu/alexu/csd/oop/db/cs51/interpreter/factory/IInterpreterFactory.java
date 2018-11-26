package eg.edu.alexu.csd.oop.db.cs51.interpreter.factory;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.cs51.interpreter.Interpreter;

public interface IInterpreterFactory {
    public Interpreter getInterpreterFromQuery(String Query) throws SQLException;
}
