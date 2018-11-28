package eg.edu.alexu.csd.oop.db.cs51.visitor;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.commands.Command;
import eg.edu.alexu.csd.oop.db.cs51.commands.InsertCommand;
import eg.edu.alexu.csd.oop.db.cs51.commands.SelectCommand;
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
	public Object visit(CreateTableInterpreter createTableInterpreter, String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DeleteInterpreter deleteInterpreter, String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DropDatabaseInterpreter databaseInterpreter, String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DropTableInterpreter dropTableInterpreter, String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(InsertInterpreter insertInterpreter, String query) throws SQLException, ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		QueryParameters qp = insertInterpreter.interpret(query);
		Command insert = new InsertCommand();
		return insert.execute(qp);
	}

	@Override
	public Object visit(SelectInterpreter selectInterpreter, String query) throws SQLException, ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		QueryParameters qp = selectInterpreter.interpret(query);
		Command select = new SelectCommand();
		return select.execute(qp);	}

	@Override
	public Object visit(UpdateInterpreter updateInterprater, String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
