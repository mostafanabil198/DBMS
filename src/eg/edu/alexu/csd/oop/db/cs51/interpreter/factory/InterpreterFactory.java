package eg.edu.alexu.csd.oop.db.cs51.interpreter.factory;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateDBInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DeleteInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropDatabaseInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.InsertInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.Interpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.SelectInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterpreter;

public class InterpreterFactory implements IInterpreterFactory{
	private static final String REGEX = "((CREATE +TABLE)|(DROP +TABLE)|(CREATE +DATABASE)|(DROP +DATABASE)|(UPDATE)|(DELETE +FROM)|(SELECT)|(INSERT +INTO)).*";

	public Interpreter getInterpreterFromQuery(String query) throws SQLException {
		Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(query);
		if (matcher.matches()) {
			String found = matcher.group(1);
			found = found.toLowerCase();
			found = found.replaceAll(" +", " ");
			switch (found) {
			case "create database":
				return new CreateDBInterpreter();
			case "create table":
				return new CreateTableInterpreter();
			case "drop database":
				return new DropDatabaseInterpreter();
			case "drop table":
				return new DropTableInterpreter();
			case "select":
				return new SelectInterpreter();
			case "insert into":
				return new InsertInterpreter();
			case "update":
				return new UpdateInterpreter();
			case "delete from":
				return new DeleteInterpreter();
			default:
				throw new SQLException();
			}
		} else {
			throw new SQLException();
		}
	}
}
