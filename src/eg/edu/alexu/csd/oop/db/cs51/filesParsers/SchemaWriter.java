package eg.edu.alexu.csd.oop.db.cs51.filesParsers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class SchemaWriter {
	
	public static void saveSchama(String path , List<Pair<String, String>> columnTybe, String tableName) {
		String columnsName = "(";
		columnsName += columnTybe.get(0).getKey();
		for(int i = 1;i < columnTybe.size();i++) {
			columnsName += ", ";
			columnsName += columnTybe.get(i).getKey();
		}
		columnsName += ")";
		
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(path)));
			bufferedWriter.append("<!DOCTYPE "+tableName);
			bufferedWriter.newLine();
			bufferedWriter.append("[");
			bufferedWriter.newLine();
			bufferedWriter.append("<!ELEMENT "+tableName+" "+columnsName+">");
			for(int i = 0;i<columnTybe.size();i++)
			{
				bufferedWriter.newLine();
				bufferedWriter.append("<!ELEMENT "+columnTybe.get(i).getKey()+" "+"("+columnTybe.get(i).getValue()+")"+">");
			}
			bufferedWriter.newLine();
			bufferedWriter.append("]>");
			bufferedWriter.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
