package telran.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.StackInt;

class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	
	@Test
	void subListTest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 70, -20));
		list.add(5);
		List<Integer> subList = list.subList(6, 9);//первый индекс включен, второй не включен 
		System.out.println(subList);
		subList.add(1, -2);
		subList.sort(Integer::compare);
		subList.clear();
		System.out.println(list);
	}

	
	@Test
	void displayOccurrencesCount() {
		String[] strings = {"lmn", "abc", "abc", "lmn", "a", "lmn"};
		Map<String, List<String>> map1 = Arrays.stream(strings).collect(Collectors.groupingBy(s -> s));
		Map<Integer, List<String>> map2 = Arrays.stream(strings).collect(Collectors.groupingBy(s -> s.length()));
		Map<String, Long> map3 = Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		System.out.println(map1);
		System.out.println(map2);
		System.out.println(map3);
		Arrays.stream(strings)
			.collect(Collectors.groupingBy(s -> s, Collectors.counting()))
			.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
			.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));
	}
	

	@Test
	void stackIntTest() {
		StackInt stack = new StackInt();
		assertThrowsExactly(NoSuchElementException.class, () -> stack.getMax());
		assertThrowsExactly(NoSuchElementException.class, () -> stack.pop());
		stack.push(1);
		stack.push(2);
		assertEquals(2, stack.pop());
		assertEquals(1, stack.pop());
		assertTrue(() -> stack.isEmpty());
		stack.push(10);
		stack.push(1000);
		stack.push(300);
		assertEquals(1000, stack.getMax());
		assertEquals(1000, stack.getMax());
		stack.push(3000);
		assertEquals(3000, stack.getMax());
		assertEquals(3000, stack.pop());
		assertEquals(1000, stack.getMax());
		assertEquals(300, stack.pop());
		assertEquals(1000, stack.getMax());
		assertEquals(1000, stack.pop());
		assertEquals(10, stack.getMax());
		assertEquals(10, stack.pop());
		assertTrue(() -> stack.isEmpty());
	}
	
	@Test
	void displayDigitStatistics() {
		
		Integer[] littleAr = fillArray(5);
		Arrays.stream(littleAr).forEach(a -> System.out.println(a));
		System.out.println();
		displayCountOfNums(littleAr);
		System.out.println();
		
		Integer[] bigArray = fillArray(1_000_000);
		displayCountOfNums(bigArray);
	}
	
	Integer[] fillArray(int size) {
		Integer[] ar = new Integer[size];
		for (int i = 0; i < ar.length; i++)
			ar[i] = 1 + (int) (Math.random() * Integer.MAX_VALUE);
		return ar;
	}
	
	void displayCountOfNums(Integer[] ar) {
		Arrays.stream(ar)
			.map(ai -> String.valueOf(ai)).flatMapToInt(ai -> ai.chars()).map(Character::getNumericValue).boxed()
			.collect(Collectors.groupingBy(s -> s, Collectors.counting()))
			.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
			.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));
	}
	
}
