package telran.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import telran.structure.Words;

public class MyWords implements Words {

	HashMap<String, String> wordsMap = new HashMap<>();
	
	@Override
	public boolean addWord(String word) {
		boolean res;
		if (!(res = wordsMap.containsKey(word.toLowerCase()))){
			wordsMap.put(word.toLowerCase(), word);
		}
		return !res;
	}

	@Override
	public List<String> getWordsByPrefix(String prefix) {
		return wordsMap.entrySet().stream()
				.filter(e -> e.getKey().startsWith(prefix.toLowerCase()))
				.filter(e -> e.getValue().startsWith(prefix))
				.map(Map.Entry::getValue).toList();
	}

}
