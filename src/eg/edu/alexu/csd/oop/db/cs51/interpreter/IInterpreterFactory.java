package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.sql.SQLException;

public interface IInterpreterFactory {
    public Interpreter getInterpreterFromQuery(String Query) throws SQLException;
}
