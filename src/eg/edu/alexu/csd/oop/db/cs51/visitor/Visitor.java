package eg.edu.alexu.csd.oop.db.cs51.visitor;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateDBInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DeleteInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropDatabaseInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.InsertInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.SelectInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterpreter;

public interface Visitor {
	public Object visit(CreateTableInterpreter createTableInterpreter, String query) throws SQLException, ParserConfigurationException, SAXException, IOException;

	public Object visit(DeleteInterpreter deleteInterpreter, String query) throws SQLException, ParserConfigurationException, SAXException, IOException;

	public Object visit(DropDatabaseInterpreter dropDatabaseInterpreter, String query) throws SQLException, ParserConfigurationException, SAXException, IOException;

	public Object visit(DropTableInterpreter dropTableInterpreter, String query) throws SQLException, ParserConfigurationException, SAXException, IOException;

	public Object visit(InsertInterpreter insertInterpreter, String query) throws SQLException;

	public Object visit(SelectInterpreter selectInterpreter, String query) throws SQLException;

	public Object visit(UpdateInterpreter updateInterprater, String query) throws SQLException, ParserConfigurationException, SAXException, IOException;

}
