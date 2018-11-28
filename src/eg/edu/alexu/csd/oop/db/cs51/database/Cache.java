package eg.edu.alexu.csd.oop.db.cs51.database;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Cache {
	private long deadTime;
	private Hashtable<Table, Long> lock, unlock;

	public Cache() {
		deadTime = 30000; // 30 seconds
		lock = new Hashtable<Table, Long>();
		unlock = new Hashtable<Table, Long>();
	}

	private Table create(String tableName) throws ParserConfigurationException, SAXException, IOException {
		return Table.loadTable(tableName);
	}

	private boolean validate(Table o, String tableName) {
		return o.getTableName().equalsIgnoreCase(tableName);

	}

	private void dead(Table o) {
		o.save();
	}

	public void shutdownCache() {
		if (unlock.size() > 0) {
			Enumeration<Table> e = unlock.keys();
			while (e.hasMoreElements()) {
				Table t = e.nextElement();
				unlock.remove(t);
				dead(t);
			}
		}
	}

	public synchronized Table takeOut(String tableName) throws ParserConfigurationException, SAXException, IOException {
		long now = System.currentTimeMillis();
		Table t;
		if (unlock.size() > 0) {
			Enumeration<Table> e = unlock.keys();
			while (e.hasMoreElements()) {
				t = e.nextElement();
				if ((now - unlock.get(t)) > deadTime) {
					// object has deadd
					unlock.remove(t);
					dead(t);
					t = null;
				} else {
					if (validate(t, tableName)) {
						unlock.remove(t);
						lock.put(t, now);
						return (t);
					}
				}
			}
		}
		// no objects available, create a new one
		t = create(tableName);
		lock.put(t, now);
		return t;
	}

	public synchronized void takeIn(Table t) {
		lock.remove(t);
		unlock.put(t, System.currentTimeMillis());

	}

	public Table removeFromCache(String tableName) throws ParserConfigurationException, SAXException, IOException {
		if (unlock.size() > 0) {
			Enumeration<Table> e = unlock.keys();
			while (e.hasMoreElements()) {
				Table t = e.nextElement();
				if (validate(t, tableName)) {
					unlock.remove(t);
					return t;

				}
			}
		}
		return create(tableName);
	}
}
