package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.*;

import telran.structure.Words;
import telran.util.MyWords;

class WordsTest {

	Words words = new MyWords();
	
	@BeforeEach
	void setUp() throws Exception {
		assertTrue(words.addWord("Artem"));
		assertTrue(words.addWord("Artemoon"));
		assertTrue(words.addWord("Artemka"));
		assertTrue(words.addWord("Arie"));
	}

	@Test
	void wordsTest() {
		List<String> list = words.getWordsByPrefix("Art");
		assertEquals(3, list.size());
		assertTrue(list.contains("Artem"));
		assertTrue(list.contains("Artemoon"));
		assertTrue(list.contains("Artemka"));
		
		List<String> list2 = words.getWordsByPrefix("Ar");
		assertEquals(4, list2.size());
		assertTrue(list2.contains("Artem"));
		assertTrue(list2.contains("Artemoon"));
		assertTrue(list2.contains("Artemka"));
		assertTrue(list2.contains("Arie"));
		
		List<String> list3 = words.getWordsByPrefix("Ara");
		assertEquals(0, list3.size());
		
		List<String> list4 = words.getWordsByPrefix("art");
		assertEquals(0, list4.size());
		
		List<String> list5 = words.getWordsByPrefix("ar");
		assertEquals(0, list5.size());
		
		List<String> list6 = words.getWordsByPrefix("A");
		assertEquals(4, list6.size());
		assertTrue(list6.contains("Artem"));
		assertTrue(list6.contains("Artemoon"));
		assertTrue(list6.contains("Artemka"));
		assertTrue(list6.contains("Arie"));
	}

}
