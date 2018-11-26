package eg.edu.alexu.csd.oop.db.cs51.filesParsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class SchemaReader {
    private static final String TABLE_NAME_REGEX = "<!DOCTYPE ([a-zA-Z_][a-zA-Z0-9_]*)";
    private static final String TABLE_ELEMENTS_REGEX = "<!ELEMENT ([a-zA-Z_][a-zA-Z0-9_]*) \\((([a-zA-Z_][a-zA-Z0-9_]*)(, (([a-zA-Z_][a-zA-Z0-9_]*)))*)\\)>";
    private static final String ELEMENT_REGEX = "<!ELEMENT ([a-zA-Z_][a-zA-Z0-9_]*) \\(((int)|(string))\\)>";
    
    public static List<Pair<String, String>> readSchema(String pathToSchema){
        File file = new File(pathToSchema);
        List<Pair<String, String>> columnType = new ArrayList<Pair<String, String>>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            
            Pattern pattern = Pattern.compile(TABLE_NAME_REGEX);
            Matcher matcher = pattern.matcher(bf.readLine());
            
            String tableName;
            
            if(matcher.matches()) {
                tableName = matcher.group(1);
            } else {
                throw new Exception();
            }
            
            if(!bf.readLine().equals("[")) throw new Exception();
            
            List<String> columns;
            String line = bf.readLine();
            pattern = Pattern.compile(TABLE_ELEMENTS_REGEX);
            matcher = pattern.matcher(line);
            if(matcher.matches()) {
                if(!matcher.group(1).equals(tableName)) throw new Exception();
                columns = parseColumns(matcher.group(2));
            } else {
                throw new Exception();
            }
            
            pattern = Pattern.compile(ELEMENT_REGEX);
            int i = 0;
            List<String> types = new ArrayList<String>();
            while(!(line = bf.readLine()).equals("]>")) {
                matcher = pattern.matcher(line);
                if(matcher.matches()) {
                    if(!matcher.group(1).equals(columns.get(i)))throw new Exception();
                    
                    String type = matcher.group(2);
                    types.add(type);
                    
                } else {
                    System.out.println(line);
                    throw new Exception();
                }
                i++;
            }
            
            return mergeColumnType(columns, types);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private  static List<String> parseColumns(String param) {
        List<String> columns = new ArrayList<String>();
        String regex = "([a-zA-Z_][a-zA-Z0-9_]*) *(, *([a-zA-Z_][a-zA-Z0-9_]*))";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(param);
        while(m.find()) {
            columns.add(m.group(1));
            param = param.replaceFirst(m.group(1), "");
            param = param.replaceFirst(",", "");
            param = param.trim();
            m = p.matcher(param);
        }
        columns.add(param);
        return columns;
    }
    
    private static List<Pair<String, String>> mergeColumnType(List<String> col, List<String> types) throws Exception{
        if(col.size() != types.size()) throw new Exception();
        List<Pair<String, String>> merged =  new ArrayList<Pair<String, String>>();
        for(int i = 0; i < types.size(); i++) {
            merged.add(new Pair<String, String>(col.get(i), types.get(i)));
        }
        return merged;
    }
}
