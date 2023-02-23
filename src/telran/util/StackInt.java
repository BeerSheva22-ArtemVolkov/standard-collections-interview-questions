package telran.util;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

public class StackInt {
	
	private static LinkedList<Integer> linkedList = new LinkedList<>();
	private static Stack<Integer> stackMax = new Stack<>();
	
	public void push(int num) {
		linkedList.add(num);
		int size = linkedList.size();
		if (size == 1 || size > 0 && num >= stackMax.peek()) {
			stackMax.add(num);
		}
	}
	
	public int pop() {
		int res = linkedList.getLast();
		linkedList.removeLast();
		if (stackMax.peek() == res) {
			stackMax.pop();
		}
		return res;
	}
	
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}
	
	public int getMax() {
		if (linkedList.isEmpty()) {
			throw new NoSuchElementException();
		}
		return stackMax.peek();
	}
}
