package eg.edu.alexu.csd.oop.db.cs51;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropDatabase;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.Interpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.SelectInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterprater;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class MainMostafa {

	public static void main(String[] args) throws SQLException {
		 String SELECT_PATTERN1= "select (([A-Za-z_][A-Za-z0-9_]*)( *, *[A-Za-z_][A-Za-z0-9_]*)*) from ([A-Za-z_][A-Za-z0-9_]*) *;*";
		 String SELECT_PATTERN2= "select (\\*) from ([A-Za-z_][A-Za-z0-9_]*) *;*";
	 String SELECT_PATTERN3= "select (([A-Za-z_][A-Za-z0-9_]*)( *, *[A-Za-z_][A-Za-z0-9_]*)*) from ([A-Za-z_][A-Za-z0-9_]*) where ([^; ]+) *;*";
		String query = "select mostafa, nabil, tarek , sala7_ , asafs from table1 where adg5 ;";
//		Pattern pattern = Pattern.compile(SELECT_PATTERN1,Pattern.CASE_INSENSITIVE);
//		Matcher matcher = pattern.matcher(query);
//		if(matcher.matches()) {
//			System.out.println(matcher.group(1));
//			System.out.println(matcher.group(4));
//			String GET_COLUMNS_PATTERN = "([A-Za-z_][A-Za-z0-9_]*)(( *,)|(\\z))";
//			Pattern p = Pattern.compile(GET_COLUMNS_PATTERN);
//			Matcher mather = p.matcher(matcher.group(1));
//			while(mather.find()) {
//				System.out.println(mather.group(1));
//			}
//			
////			System.out.println(matcher.group(1));
////			System.out.println(matcher.group(2));
//			System.out.println(matcher.group(1));
//			System.out.println(matcher.group(4));
//			System.out.println(matcher.group(5));
			

		
	SelectInterpreter s = new SelectInterpreter();
		QueryParameters q = s.interpret(query);
		List<String> ss = q.getColumnsName();
		for(int i = 0; i < ss.size(); i++) {
			System.out.println(ss.get(i));
		}
		System.out.println(q.getCondition());
		System.out.println(q.getTableName());
		
		

	}

}
