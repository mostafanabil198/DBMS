package eg.edu.alexu.csd.oop.db.cs51;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.filesParsers.TableWriter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.Interpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.SelectInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterprater;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class MainMostafa {

	public static void main(String[] args) throws SQLException {
		List<Map<String,String>> list = new ArrayList<>();
//		Map m = new HashMap<>();
//		m.put("id", "1");
//		m.put("name", "mostafa");
//		m.put("age", "20");
//		m.put("gender", "male");
//		list.add(m);
//		
//		m = new HashMap<>();
//		m.put("id", "2");
//		m.put("name", "nashar");
//		m.put("age", "20");
//		m.put("gender", "male");
//		list.add(m);
//		
//		m = new HashMap<>();
//		m.put("id", "3");
//		m.put("name", "tarek");
//		m.put("age", "21");
//		m.put("gender", "male");
//		list.add(m);
//		
//		m = new HashMap<>();
//		m.put("id", "4");
//		m.put("name", "sala7");
//		m.put("age", "21");
//		m.put("gender", "male");
//		list.add(m);
		
		TableWriter tw = new TableWriter();
		tw.saveTable(new File("saveMostafa.xml"), list);
	}

}
