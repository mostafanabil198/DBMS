package eg.edu.alexu.csd.oop.db.cs51.commands;

import eg.edu.alexu.csd.oop.db.cs51.QueryParameters;

public interface Command {
    public Object execute(QueryParameters qp);
}
