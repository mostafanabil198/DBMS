package eg.edu.alexu.csd.oop.db.cs51.visitor;

import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateDBInterpereter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.CreateTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DeleteInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropDatabaseInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.DropTableInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.InsertInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.SelectInterpreter;
import eg.edu.alexu.csd.oop.db.cs51.interpreter.UpdateInterprater;

public class InterpretersVisitor implements Visitor{

	@Override
	public void visit(CreateDBInterpereter createDBInterpereter, String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CreateTableInterpreter createTableInterpreter, String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DeleteInterpreter deleteInterpreter, String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DropDatabaseInterpreter databaseInterpreter, String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DropTableInterpreter dropTableInterpreter, String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InsertInterpreter insertInterpreter, String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SelectInterpreter selectInterpreter, String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(UpdateInterprater updateInterprater, String query) {
		// TODO Auto-generated method stub
		
	}

}
