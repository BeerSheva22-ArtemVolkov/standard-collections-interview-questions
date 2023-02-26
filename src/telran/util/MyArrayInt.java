package telran.util;

import java.util.HashMap;

public class MyArrayInt {
	
	HashMap<Integer, Integer> hm;
	int size;
	int value;
	
	public MyArrayInt(int size, int value){
		this.size = size;
		this.value = value;
	}
	
	public void set(int index, int value){
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (hm == null) {
			hm = new HashMap<>();
		}
		hm.put(index, value);
	}
	
	public int get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		int res = value;
		if (hm != null) {
			res = hm.getOrDefault(hm, value);
		}
		return res;
	}
	
	public void setAll(int value) {
		this.value = value;
		hm = null;
	}
}
