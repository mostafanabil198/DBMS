package eg.edu.alexu.csd.oop.db.cs51.visitor;

import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateDBInterpereter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DeleteInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropDatabaseInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.InsertInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.SelectInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterprater;

public interface Visitor {
public void visit(CreateDBInterpereter createDBInterpereter ,String query);
public void visit(CreateTableInterpreter createTableInterpreter,String query);
public void visit(DeleteInterpreter deleteInterpreter,String query);
public void visit(DropDatabaseInterpreter databaseInterpreter,String query);
public void visit(DropTableInterpreter dropTableInterpreter,String query);
public void visit(InsertInterpreter insertInterpreter,String query);
public void visit(SelectInterpreter selectInterpreter,String query);
public void visit(UpdateInterprater updateInterprater,String query);

}
