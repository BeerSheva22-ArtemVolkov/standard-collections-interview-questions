package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import telran.util.StackInt;

import java.util.*;
import java.util.stream.Collectors;

class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Disabled
	@Test
	void subListTest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 70, -20));
		list.add(5);
		List<Integer> subList = list.subList(6, 9);// первый индекс включен, второй не включен
		System.out.println(subList);
		subList.add(1, -2);
		subList.sort(Integer::compare);
		subList.clear();
		System.out.println(list);
	}

	@Disabled
	@Test
	void displayOccurrencesCount() {
		String[] strings = { "lmn", "abc", "abc", "lmn", "a", "lmn" };
		Map<String, List<String>> map1 = Arrays.stream(strings).collect(Collectors.groupingBy(s -> s));
		Map<Integer, List<String>> map2 = Arrays.stream(strings).collect(Collectors.groupingBy(s -> s.length()));
		Map<String, Long> map3 = Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		System.out.println(map1);
		System.out.println(map2);
		System.out.println(map3);
		Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));
	}

	@Disabled
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
		stack.push(1000);
		stack.push(300);
		assertEquals(1000, stack.getMax());
		stack.push(3000);
		assertEquals(3000, stack.getMax());
		assertEquals(3000, stack.pop());
		assertEquals(1000, stack.getMax());
		assertEquals(300, stack.pop());
		assertEquals(1000, stack.getMax());
		assertEquals(1000, stack.pop());
		assertEquals(1000, stack.getMax());
		assertEquals(1000, stack.pop());
		assertEquals(10, stack.getMax());
		assertEquals(10, stack.pop());
		assertTrue(() -> stack.isEmpty());
	}

	@Disabled
	@Test
	void displayDigitStatistics() {

		Integer[] littleAr = fillArray(5);
		Arrays.stream(littleAr).forEach(a -> System.out.println(a));
		System.out.println();
		displayCountOfNums(littleAr);
		System.out.println();

		Integer[] bigArray = fillArray(1_000_000);
		displayCountOfNums(bigArray);

		new Random().ints(1_000_000, 1, Integer.MAX_VALUE).flatMap(n -> Integer.toString(n).chars()).boxed()// .mapToObj(x
																											// -> x)
																											// //.map(Character::getNumericValue)
				.collect(Collectors.groupingBy(digit -> digit, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				// .forEach(e -> System.out.printf("%c: %d\n", e.getKey(), e.getValue()));
				.forEach(e -> System.out.printf("%d: %d\n", e.getKey() - '0', e.getValue()));
	}

	Integer[] fillArray(int size) {
		Integer[] ar = new Integer[size];
		for (int i = 0; i < ar.length; i++)
			ar[i] = 1 + (int) (Math.random() * Integer.MAX_VALUE);
		return ar;
	}

	void displayCountOfNums(Integer[] ar) {
		Arrays.stream(ar).map(ai -> String.valueOf(ai)).flatMapToInt(ai -> ai.chars()).map(Character::getNumericValue)
				.boxed().collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));
	}

	@Test
	void maxNumberWithNegativeImageTest() {
		int ar[] = { 10_000_000, 3, -2, -200, 200, -3, 2 };
		int ar1[] = { 1_000_000, -1_000_000_000, 3, -4 };
		assertEquals(200, maxNumberWithNegativeImage(ar));
		assertEquals(-1, maxNumberWithNegativeImage(ar1));
	}

	int maxNumberWithNegativeImage(int[] ar) {

		int res = -1;
		Set<Integer> set = new HashSet<>();
		int abs;

		for (int num : ar) {
			if (set.contains(-num) && (abs = Math.abs(num)) > res) {
				res = abs;
			} else {
				set.add(num);
			}
		}
		return res;
	}

	@Test
	void treeIteratingTest() {
		Integer[] array = { 1, 11, 111, 32, 9, 1234, 99, 992 };
		createAndIterateInOrder(array);
	}

	class numSumComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return numSum(o1).compareTo(numSum(o2));
		}

	}

	private Integer numSum(Integer num) {
		return numSum(num, 0);
	}

	private Integer numSum(Integer num, Integer sum) {
		sum += num % 10;
		return num / 10 == 0 ? sum : numSum(num / 10, sum);
	}

	
	private void createAndIterateInOrder(Integer[] array) {
		
//		TreeSet<Integer> tree = new TreeSet<>(new numSumComparator());
//		Iterator<Integer> it = tree.iterator();
//		int i = 0;
//
//		for (int j = 0; j < array.length; j++) {
//			tree.add(array[j]);
//		}
//
//		while (it.hasNext()) {
//			assertEquals(array[i++], it.next());
//		}
		
		//
		
		TreeSet<Integer> set = new TreeSet<>((num1, num2) -> Integer.compare(getSumDigits(num1), getSumDigits(num2)));
		for (int num : array) {
			set.add(num);
		}
		assertArrayEquals(array, set.toArray(new Integer[0]));
		
	}

	private int getSumDigits(Integer num1) {
		return num1.toString().chars().map(c -> c - '0').sum();
	}

}
