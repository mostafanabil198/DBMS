package eg.edu.alexu.csd.oop.db.cs51;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs51.database.CurrentDatabase;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateDBInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.Interpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.factory.IInterpreterFactory;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.factory.InterpreterFactory;
import eg.edu.alexu.csd.oop.db.cs51.visitor.InterpretersVisitor;
import eg.edu.alexu.csd.oop.db.cs51.visitor.Visitor;

public class DBMS implements Database {
	
	public DBMS() {
		CurrentDatabase.getInstance().clear();
	}

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		CurrentDatabase.getInstance().createDatabase(databaseName, dropIfExists);
		return CurrentDatabase.getInstance().getPath();

	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		query = query.toLowerCase().trim();
		IInterpreterFactory factory = new InterpreterFactory();
		Interpreter interpreter = factory.getInterpreterFromQuery(query);

		if (CreateDBInterpreter.class.isAssignableFrom(interpreter.getClass())) {
			QueryParameters queryParameters = interpreter.interpret(query);
			String databaseName = queryParameters.getDatabaseName();
			createDatabase(databaseName, false);
			return true;
		} else {
			Visitor visitor = new InterpretersVisitor();
			try {
				return (boolean) interpreter.accept(visitor, query);
			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		query = query.toLowerCase().trim();
		IInterpreterFactory factory = new InterpreterFactory();
		Interpreter interpreter = factory.getInterpreterFromQuery(query);
		Visitor visitor = new InterpretersVisitor();
		try {
			return (Object[][]) interpreter.accept(visitor, query);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		query = query.toLowerCase().trim();
		IInterpreterFactory factory = new InterpreterFactory();
		Interpreter interpreter = factory.getInterpreterFromQuery(query);
		Visitor visitor = new InterpretersVisitor();
		try {
			return (int) interpreter.accept(visitor, query);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
