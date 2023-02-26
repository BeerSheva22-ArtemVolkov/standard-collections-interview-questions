package telran.util;

import java.util.LinkedList;

public class StackInt {
	
	private static LinkedList<Integer> linkedList = new LinkedList<>();
	private static LinkedList<Integer> maxNumbers = new LinkedList<>();
	
	public void push(int num) {
		linkedList.addLast(num);
		if (maxNumbers.isEmpty() || num >= maxNumbers.getLast()) {
			maxNumbers.addLast(num);
		}
	}
	
	public int pop() {
		int res = linkedList.removeLast();
		if (res == maxNumbers.getLast()) {
			maxNumbers.removeLast();
		}
		return res;
	}
	
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}
	
	public int getMax() {
		return maxNumbers.getLast();
	}
}
