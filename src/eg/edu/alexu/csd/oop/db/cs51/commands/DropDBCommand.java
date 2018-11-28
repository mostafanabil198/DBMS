package eg.edu.alexu.csd.oop.db.cs51.commands;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.database.CurrentDatabase;

public class DropDBCommand implements Command{
	

    @Override
    public Object execute(QueryParameters qp) {
        // TODO Auto-generated method stub
    	
    	String databasePath = qp.getDatabaseName();
        return CurrentDatabase.getInstance().dropDatabase(databasePath);
    }


}
