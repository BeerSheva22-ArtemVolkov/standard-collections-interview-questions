package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.structure.MultiCounters;
import telran.util.MultiCountersImpl;
import telran.util.MyMultiCounter;

class MultiCountersTrest {

	MultiCounters multiCounters;
	
	@BeforeEach
	void setUp() throws Exception {
		multiCounters = new MultiCountersImpl();
		multiCounters.addItem(10);
		multiCounters.addItem(10);
		multiCounters.addItem(10);
		multiCounters.addItem("abc");
		multiCounters.addItem("abc");
		multiCounters.addItem("lmn");
	}
	@Test
	void myMultiCountertest() {
		MyMultiCounter mc = new MyMultiCounter();
		mc.addItem(100);
		mc.addItem(100);
		mc.addItem(2);
		mc.addItem(3);
		mc.addItem(100);
		mc.addItem(2);
		mc.addItem(-10);
		mc.addItem(-10);
		Set<Object> hs = mc.getMaxItems();
		assertTrue(hs.contains(100));
		mc.remove(100);
		hs = mc.getMaxItems();
		assertTrue(hs.contains(2));
		assertTrue(hs.contains(-10));
		mc.remove(2);
		hs = mc.getMaxItems();
		assertTrue(hs.contains(-10));
		assertEquals(1, hs.size());
		mc.remove(3);
		hs = mc.getMaxItems();
		assertTrue(hs.contains(-10));
		assertEquals(1, hs.size());
	}
	
	@Test
	void myMultiCountertest2() {
		MyMultiCounter mc = new MyMultiCounter();
		mc.addItem("Julia");
		mc.addItem("Julia");
		mc.addItem("Artem");
		mc.addItem("Alexey");
		mc.addItem("Julia");
		mc.addItem("Artem");
		mc.addItem("Yana");
		mc.addItem("Yana");
		Set<Object> hs = mc.getMaxItems();
		assertTrue(hs.contains("Julia"));
		mc.remove("Julia");
		hs = mc.getMaxItems();
		assertTrue(hs.contains("Artem"));
		assertTrue(hs.contains("Yana"));
		mc.remove("Artem");
		hs = mc.getMaxItems();
		assertTrue(hs.contains("Yana"));
		assertEquals(1, hs.size());
		mc.remove("Alexey");
		hs = mc.getMaxItems();
		assertTrue(hs.contains("Yana"));
		assertEquals(1, hs.size());
	}
	
	@Test
	void getMaxItemsTest() {
		runTest(Arrays.asList(10));
	}
	private void runTest(List<Object> list) {
		var set = multiCounters.getMaxItems();
		list.forEach((item) -> assertTrue(set.contains(item)));
	}
	
	@Test
	void getValueTest() {
		assertEquals(3, multiCounters.getValue(10));
		assertNull(multiCounters.getValue("kuku"));
	}
	
	@Test
	void addItemTest() {
		Object[] items = {10, "abc"};
		assertEquals(3, multiCounters.addItem("abc"));
		runTest(Arrays.asList(items));
	}
	
	@Test
	void removeItemTest() {
		Object[] items = {"abc", "lmn"};
		assertTrue(multiCounters.remove(10));
		runTest(Arrays.asList(items));
		assertFalse(multiCounters.remove(10));
	}
}
