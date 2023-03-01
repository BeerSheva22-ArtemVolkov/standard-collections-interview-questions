package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.structure.Connection;
import telran.structure.ConnectionsPool;
import telran.util.MyConnectionsPool;

class ConnectionsPoolTest {

	ConnectionsPool conPool = new MyConnectionsPool(10);
	
	@BeforeEach
	void setUp() throws Exception {
		conPool.addConnection(new Connection(1, "123.123.123", 0));
		conPool.addConnection(new Connection(2, "123.123.123", 10));
		conPool.addConnection(new Connection(3, "123.123.123", 20));
		conPool.addConnection(new Connection(4, "123.123.123", 30));
		conPool.addConnection(new Connection(5, "123.123.123", 40));
		conPool.addConnection(new Connection(6, "123.123.123", 50));
		conPool.addConnection(new Connection(7, "123.123.123", 60));
		conPool.addConnection(new Connection(8, "123.123.123", 70));
		conPool.addConnection(new Connection(9, "123.123.123", 80));
		conPool.addConnection(new Connection(10, "123.123.123", 90));
	}

	@Test
	void connectionsPoolTest() {
		assertTrue(conPool.addConnection(new Connection(11, "123.123.123", 100)));
		assertNull(conPool.getConnection(1));
		conPool.getConnection(2);
		assertTrue(conPool.addConnection(new Connection(12, "123.123.123", 110)));
		assertNull(conPool.getConnection(3));
		assertFalse(conPool.addConnection(new Connection(5, "123.123.123", 110)));
	}

}
