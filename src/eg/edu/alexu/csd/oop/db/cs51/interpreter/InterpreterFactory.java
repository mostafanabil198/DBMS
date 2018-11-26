package eg.edu.alexu.csd.oop.db.cs51.interpreter;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterpreterFactory implements IInterpreterFactory{
	private static final String REGEX = "((CREATE +TABLE)|(DROP +TABLE)|(CREATE +DATABASE)|(DROP +DATABASE)|(UPDATE)|(DELETE +FROM)|(SELECT)|(INSERT +INTO)).*";
    
	@Override
    public Interpreter getInterpreterFromQuery(String query) {
        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(query);
        if(matcher.matches()) {
            String found = matcher.group(1);
            found = found.toLowerCase();
            found = found.replaceAll(" +", " ");
            switch(found) {
                case "create database":
                    return new CreateDBInterpereter();
                case "create table":
                    return new CreateTableInterpreter();
                case "drop database":
                    return new DropDatabaseInterpreter();
                case "drop table":
                    return new DropTableInterpreter();
                case "select into":
                    return new selectInterpreter();
                case "insert":
                    return new InsertInterpreter();
                case "update":
                    return new UpdateInterprater();
                case "delete from":
                    return new DeleteInterpreter();
            }
        } else {
            throw new SQLException();
        }
    }
}
