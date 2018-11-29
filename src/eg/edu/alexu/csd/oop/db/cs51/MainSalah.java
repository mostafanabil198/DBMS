package eg.edu.alexu.csd.oop.db.cs51;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.Filters.Filter;
import eg.edu.alexu.csd.oop.db.cs51.Filters.EqualFilter;
import eg.edu.alexu.csd.oop.db.cs51.filesParsers.SchemaReader;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.InsertInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.Interpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class MainSalah {

    public static void main(String[] args) throws SQLException {
        DBMS dbms = new DBMS();
        dbms.createDatabase("sample/db1", true);
        dbms.executeStructureQuery("Create table shehab (answer varchar, feature varchar, range_3lo2ya int, discussion_rate int)");
        dbms.executeUpdateQuery("insert into shehab values('createDB', '3lo2ya', '15', '1000');");
        dbms.executeUpdateQuery("insert into shehab values('createDB', '3lo2ya', '17', '10000');");
        dbms.executeUpdateQuery("insert into shehab values('createDB', 'mnyka', '15', '5000');");
        dbms.executeUpdateQuery("insert into shehab values('mfesh bonus', 'sharmata', '10', '2000');");
        dbms.executeUpdateQuery("insert into shehab values('mfesh bonus', '3lo2ya', '10', '3000');");
        
        Object[][] nnn = dbms.executeQuery("select * from shehab where answer='mfesh bonus' and (feature = 'mnyka' or not range_3lo2ya = 15);");
        for(int i = 0; i < nnn.length; i++) {
            for(int j = 0; j < nnn[i].length; j++) {
                System.out.print(nnn[i][j] + "     ");
            }
            System.out.println("================");
        }
    }

}
