package WordCheck;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class WordCheckTest {
	WordCheck word = new WordCheck();
	@Test
	void testValidWords() {
		int[] userInput = new int[] { 2, 3, 4 };
		word.ReadFile("TestFilePath");
		ArrayList<String> availableWords = word.keyInput(userInput);
		
		assertEquals(2, availableWords.size());
		assertEquals("HELP", availableWords.get(0));
		assertEquals("PEHL", availableWords.get(1));
		
	}
}
