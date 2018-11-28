package eg.edu.alexu.csd.oop.db.cs51;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.cs51.database.CurrentDatabase;
import eg.edu.alexu.csd.oop.db.cs51.database.Table;
import eg.edu.alexu.csd.oop.db.cs51.filesParsers.SchemaWriter;
import eg.edu.alexu.csd.oop.db.cs51.filesParsers.TableLoader;
import eg.edu.alexu.csd.oop.db.cs51.filesParsers.TableWriter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.Interpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.SelectInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class MainMostafa {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, SQLException {
//		CurrentDatabase.getInstance().createDatabase("7a7a", false);
//		createTable();
		String s = "Create TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)";
		DBMS d = new DBMS();
		d.createDatabase("sample" + System.getProperty("file.separator") + "mmm", false);
		d.executeStructureQuery(s);

	}

	public static void createTable() throws ParserConfigurationException, SAXException, IOException, SQLException {
		List<Map<String, String>> list = new ArrayList<>();
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
		tw.saveTable(new File("7a7a/saveMostafa1.xml"), list);

		SchemaWriter sw = new SchemaWriter();
		List<Pair<String, String>> colType = new ArrayList<>();
		Pair<String, String> p = new Pair("id", "int");
		colType.add(p);
		p = new Pair("name", "string");
		colType.add(p);
		p = new Pair("age", "int");
		colType.add(p);
		p = new Pair("gender", "string");
		colType.add(p);
		sw.saveSchama("7a7a/saveMostafa1.dtd", colType, "saveMostafa1");
		
		Table t = CurrentDatabase.getInstance().getTableFromCache("saveMostafa1");
		List<String>  l = new ArrayList<>();
		l.add("id");
		Object[][] tb =  t.select(l);
		for(int i = 0; i < tb.length; i++) {
			for(int y = 0; y<tb[i].length; y++) {
				System.out.println(tb[i][y]);
			}
		}
		CurrentDatabase.getInstance().cacheTable(t);
		
		 t = CurrentDatabase.getInstance().getTableFromCache("saveMostafa1");
			  l = new ArrayList<>();
			l.add("name");
			tb =  t.select(l);
			for(int i = 0; i < tb.length; i++) {
				for(int y = 0; y<tb[i].length; y++) {
					System.out.println(tb[i][y]);
				}
			}
			CurrentDatabase.getInstance().cacheTable(t);
		
		

	}

}
