package eg.edu.alexu.csd.oop.db.cs51.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;

public interface Command {
    public Object execute(QueryParameters qp) throws ParserConfigurationException, SAXException, IOException, SQLException;
}
