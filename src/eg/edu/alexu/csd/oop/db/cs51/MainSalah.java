package eg.edu.alexu.csd.oop.db.cs51;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.db.cs51.Filters.Filter;
import eg.edu.alexu.csd.oop.db.cs51.Filters.EqualFilter;
import eg.edu.alexu.csd.oop.db.cs51.filesParsers.SchemaReader;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.InsertInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.Interpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class MainSalah {

    public static void main(String[] args) {
        Interpreter i = new UpdateInterpreter();
        try {
            i.interpret("update ali set a = 7, name = 'alii'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
