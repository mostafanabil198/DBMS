package eg.edu.alexu.csd.oop.db.cs51.commands;

import java.util.List;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;
import eg.edu.alexu.csd.oop.db.cs51.database.CurrentDatabase;
import eg.edu.alexu.csd.oop.db.cs51.database.Table;
import eg.edu.alexu.csd.oop.db.cs51.utilities.Pair;

public class CreateTableCommand implements Command {

    @Override
    public Object execute(QueryParameters qp) {
        String tableName = qp.getTableName();
        List<Pair<String, String>> columnType = qp.getColumnsType();
        if(!CurrentDatabase.getInstance().createNewTable(tableName)) return false; 
        Table table = Table.createNewTable(tableName, columnType);
        CurrentDatabase.getInstance().cacheTable(table);
        return true;
    }
    

}
