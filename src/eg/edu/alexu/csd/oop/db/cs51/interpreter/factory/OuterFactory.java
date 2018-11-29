package eg.edu.alexu.csd.oop.db.cs51.interpreter.factory;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateDBInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DeleteInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropDatabaseInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.InsertInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.Interpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.SelectInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterpreter;

public class OuterFactory implements IOuterFactory {
	private static final String REGEX = "((CREATE +TABLE)|(DROP +TABLE)|(CREATE +DATABASE)|(DROP +DATABASE)|(UPDATE)|(DELETE +FROM)|(SELECT)|(INSERT +INTO)).*";

	@Override
	public Object getOutput(String query, Database dbms) throws SQLException {
		Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(query);
		if (matcher.matches()) {
			String found = matcher.group(1);
			found = found.toLowerCase();
			found = found.replaceAll(" +", " ");
			switch (found) {
			case "create database":
				return (boolean) dbms.executeStructureQuery(query);
			case "create table":
				return (boolean) dbms.executeStructureQuery(query);
			case "drop database":
				return (boolean) dbms.executeStructureQuery(query);
			case "drop table":
				return (boolean) dbms.executeStructureQuery(query);
			case "select":
				return (Object[][]) dbms.executeQuery(query);
			case "insert into":
				return (int) dbms.executeUpdateQuery(query);
			case "update":
				return (int) dbms.executeUpdateQuery(query);
			case "delete from":
				return (int) dbms.executeUpdateQuery(query);
			default:
				return "Syntax error! Not a sql Query";
			}
		} else {
			return "Syntax error! Not a sql Query";
		}
	}

}
