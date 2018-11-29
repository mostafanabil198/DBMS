package eg.edu.alexu.csd.oop.db.cs51;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs51.gui.DBMSGui;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.factory.IOuterFactory;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.factory.OuterFactory;

public class Main {

	public static void main(String[] args) {
		DBMSGui gui = new DBMSGui();
		Database dbms = new DBMS();
		IOuterFactory outerFactory = new OuterFactory();
		gui.initialize(dbms, outerFactory);
		gui.setVisible(true);
		gui.setLocationRelativeTo(null);
		

	}

}
