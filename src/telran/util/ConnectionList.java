package telran.util;

import java.util.HashMap;
import java.util.LinkedList;

import telran.structure.Connection;

public class ConnectionList {

	HashMap<Integer, Connection> map = new HashMap<>();
	LinkedList<Connection> list = new LinkedList<>();
	int size = 0;
	
	public boolean add(Connection connection) {
		boolean res;
		if (res = !map.containsKey(connection.getId())) {
			map.put(connection.getId(), connection);
			list.addFirst(connection);
			size++;
		}
		return res;
	}

	public boolean remove(Connection connection) {
		boolean res;
		if (res = map.containsKey(connection.getId())) {
			map.remove(connection.getId());
			list.remove(connection);
			size--;
		}
		return res;
	}

	public boolean contains(Connection connection) {
		return map.containsKey(connection.getId());
	}

	public int getSize() {
		return size;
	}
	
	public void removeLast() {
		map.remove(list.removeLast().getId());
		size--;
	}

	public Connection getConnection(int id) {
		return map.get(id);
	}
	
}
