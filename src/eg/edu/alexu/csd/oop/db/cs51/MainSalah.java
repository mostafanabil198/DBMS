package eg.edu.alexu.csd.oop.db.cs51;

import java.util.List;

import eg.edu.alexu.csd.oop.db.cs51.filesParsers.SchemaReader;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class MainSalah {

    public static void main(String[] args) {
        List<Pair<String, String>> colType = SchemaReader.readSchema("check.dtd");
        for(Pair<String, String> p : colType) {
            System.out.println(p.getKey() + " : " + p.getValue());
        }
    }

}
