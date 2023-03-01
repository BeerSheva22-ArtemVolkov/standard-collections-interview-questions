package telran.util;

import telran.structure.*;

public class MyConnectionsPool implements ConnectionsPool {

	private int MAX_CONNECTIONS_COUNT = 10;

	private ConnectionList conList = new ConnectionList();

	public MyConnectionsPool(int max){
		MAX_CONNECTIONS_COUNT = max;
	}
	
	public MyConnectionsPool(){
	}

	@Override
	public boolean addConnection(Connection connection) {
		boolean res = false;
		if (!conList.contains(connection)) {
			if (conList.getSize() == MAX_CONNECTIONS_COUNT) {
				conList.removeLast();
			}
			conList.add(connection);
			res = true;
		}
		return res;
	}

	@Override
	public Connection getConnection(int id) {
		Connection con = conList.getConnection(id);
		if (con != null) {
			conList.remove(con);
			conList.add(con);
		}
		return con;
	}

}
