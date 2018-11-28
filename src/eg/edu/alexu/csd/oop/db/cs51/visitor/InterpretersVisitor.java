package eg.edu.alexu.csd.oop.db.cs51.visitor;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.commands.Command;
import eg.edu.alexu.csd.oop.db.cs51.commands.CreateTableCommand;
import eg.edu.alexu.csd.oop.db.cs51.commands.DeleteCommand;
import eg.edu.alexu.csd.oop.db.cs51.commands.DropDBCommand;
import eg.edu.alexu.csd.oop.db.cs51.commands.DropTableCommand;
import eg.edu.alexu.csd.oop.db.cs51.commands.UpdateCommand;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateDBInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DeleteInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropDatabaseInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.InsertInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.SelectInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterpreter;

public class InterpretersVisitor implements Visitor {

	@Override
	public Object visit(CreateTableInterpreter createTableInterpreter, String query)
			throws SQLException, ParserConfigurationException, SAXException, IOException {
		QueryParameters paramaeters = createTableInterpreter.interpret(query);
		Command createTableCommand = new CreateTableCommand();
		return createTableCommand.execute(paramaeters);
	}

	@Override
	public Object visit(DeleteInterpreter deleteInterpreter, String query) throws SQLException, ParserConfigurationException, SAXException, IOException {
		QueryParameters paramaeters = deleteInterpreter.interpret(query);
		Command deleteCommand = new DeleteCommand();
		return deleteCommand.execute(paramaeters);
	}

	@Override
	public Object visit(DropDatabaseInterpreter dropDatabaseInterpreter, String query) throws SQLException, ParserConfigurationException, SAXException, IOException {
		QueryParameters qp = dropDatabaseInterpreter.interpret(query);
		Command command = new DropDBCommand();
		return command.execute(qp);
	}

	@Override
	public Object visit(DropTableInterpreter dropTableInterpreter, String query) throws SQLException, ParserConfigurationException, SAXException, IOException {
	    QueryParameters qp = dropTableInterpreter.interpret(query);
	    Command command = new DropTableCommand();
	    return command.execute(qp);
	}

	@Override
	public Object visit(InsertInterpreter insertInterpreter, String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(SelectInterpreter selectInterpreter, String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(UpdateInterpreter updateInterprater, String query) throws SQLException, ParserConfigurationException, SAXException, IOException {
	    QueryParameters qp = updateInterprater.interpret(query);
	    Command command = new UpdateCommand();
	    return command.execute(qp);
	}

}
