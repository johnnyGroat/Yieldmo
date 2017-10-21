package WordCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordCheck {
	private Map<Integer, String> letterMap = new HashMap<Integer, String>();  
	private ArrayList<String> wordDictonary = new ArrayList<String>();
	
	public WordCheck() {
		letterMap.put(0, "ABC");
		letterMap.put(1, "DEF");
		letterMap.put(2, "GHIJ");
		letterMap.put(3, "KLM");
		letterMap.put(4, "NOP");
		letterMap.put(5, "QRST");
		letterMap.put(6, "UV");
		letterMap.put(7, "W");
		letterMap.put(8, "XY");
		letterMap.put(9, "Z");
	}
	
	public void ReadFile(String filePath) {
		// mock read file from fileSystem
		// for now just return a few words in a list
		
		// running out of time, so leaving out the fileRead
		wordDictonary.add("HELP");
		wordDictonary.add("PEHL");
		wordDictonary.add("ZAP");
		wordDictonary.add("COMPUTER");
		wordDictonary.add("SOFTWARE");
	}
	
	public ArrayList<String> keyInput(int[] input) {
		if (input.length == 0) {
			return new ArrayList<String>();
		}
		ArrayList<String> possibleWords = wordDictonary;
		for(int i=0; i < input.length; i++) {
			// invalid input parameter
			if (input[i] < 0 || input[i] > 9) {
				return new ArrayList<String>();
			}

			ArrayList<String> words = new ArrayList<String>();
			String letters = letterMap.get(input[i]);
			for (String word : possibleWords) {
				for (char letter : letters.toCharArray()) {
					// limit the dictionary we iterate through that contain some letter from the input values
					if (word.indexOf(letter) != -1) {
						words.add(word);
					}
				}
			}
			possibleWords = words;
		}
		
		return possibleWords;
	}
	
}
