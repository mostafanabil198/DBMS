package eg.edu.alexu.csd.oop.db.cs51;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.filesParsers.TableLoader;
import eg.edu.alexu.csd.oop.db.cs51.filesParsers.TableWriter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.Interpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.SelectInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterprater;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class MainMostafa {

	public static void main(String[] args) throws SQLException, ParserConfigurationException, SAXException, IOException {
/*		List<Map<String,String>> list = new ArrayList<>();
		Map m = new HashMap<>();
		m.put("id", "1");
		m.put("name", "mostafa");
		m.put("age", "20");
		m.put("gender", "male");
		list.add(m);
		
		m = new HashMap<>();
		m.put("id", "2");
		m.put("name", "nashar");
		m.put("age", "20");
		m.put("gender", "male");
		list.add(m);
		
		m = new HashMap<>();
		m.put("id", "3");
		m.put("name", "tarek");
		m.put("age", "21");
		m.put("gender", "male");
		list.add(m);
		
		m = new HashMap<>();
		m.put("id", "4");
		m.put("name", "sala7");
		m.put("age", "21");
		m.put("gender", "male");
		list.add(m);
		
		TableWriter tw = new TableWriter();
		tw.saveTable(new File("saveMostafa.xml"), list);*/
		
		TableLoader tl = new TableLoader();
		List<Map<String,String>> l = tl.load(new File("saveMostafa.xml"));
		for(Map<String, String> row : l ) {
//			System.out.println("Row");
//			for(String s : row.keySet()) {
//				System.out.println(s + " : " + row.get(s));
//			}
			System.out.println(row.size());
		}
	}

}
