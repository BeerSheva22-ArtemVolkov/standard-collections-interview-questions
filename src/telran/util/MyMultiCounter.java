package telran.util;

import java.util.*;

import telran.structure.MultiCounters;

public class MyMultiCounter implements MultiCounters {

	private Integer maxCount = 0;
	private TreeMap<Integer, HashSet<Object>> maxValuesTree = new TreeMap<>();
	private HashMap<Object, Integer> valueMap = new HashMap<>();

	@Override
	public Integer addItem(Object item) {
		int res = 1;
		if (valueMap.containsKey(item)) {
			res = valueMap.get(item) + 1;
			valueMap.replace(item, res);
			maxValuesTree.get(res - 1).remove(item);
			if (maxValuesTree.get(res - 1).isEmpty()) {
				maxValuesTree.remove(res - 1);
			}
		} else {
			valueMap.put(item, res);
		}
		if (!maxValuesTree.containsKey(res)) {
			maxValuesTree.put(res, new HashSet<>());
		}
		maxValuesTree.get(res).add(item);
		if (res > maxCount) {
			maxCount = res;
		}
		return res;
	}

	@Override
	public Integer getValue(Object item) {
		return valueMap.get(item);
	}

	@Override
	public boolean remove(Object item) {
		boolean res = false;
		Integer value;
		if ((value = valueMap.remove(item)) != null) {
			res = maxValuesTree.get(value).remove(item);
			if (maxValuesTree.get(value).isEmpty()) {
				maxValuesTree.remove(value);
				maxCount = maxValuesTree.lastKey();
			}
		}
		return res;
	}

	@Override
	public Set<Object> getMaxItems() {
		return maxValuesTree.get(maxCount);
	}

}
