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

    public static void main(String[] args) {
        String regex = "((\\()|(\\))|(and)|(or)|(not)|(([a-zA-Z_][a-zA-Z0-9_]* *(=|>|<) *(('[^']+')|(\\d+)|(\"[^\"]+\")))))";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher("(not (a = 'ddd' or b = 5)) and(c = 9 or(d = 9 and f = \"hhhh\"))");
        List<String> exp = new ArrayList<String>();
        Stack<String> priorty = new Stack<String>();
        while(m.find()) {
            exp.add(m.group(1));
        }
        
        Stack<String> prefix = new Stack<String>();
        Stack<String> postfix = new Stack<String>();
        
        
        while(!exp.isEmpty()) {
            String part = exp.get(0);
            exp.remove(0);
            if(part.equals(")")) {
                while(!postfix.peek().equals("(")) {
                    prefix.add(postfix.pop());
                }
                postfix.pop();
            } else if (part.equals("(")) {
                postfix.add(part);
            } else if (part.matches("(not)|(and)|(or)")) {
                if((!postfix.isEmpty()) && postfix.peek().matches("(not)|(and)|(or)")) {
                    prefix.add(postfix.pop());
                }
                postfix.add(part);
            } else {
                prefix.add(part);
            }
        }
        
        while(!postfix.isEmpty()) {
            prefix.add(postfix.pop());
        }
        
        while(!prefix.isEmpty()) {
            System.out.println(prefix.pop());
        }
    }

}
