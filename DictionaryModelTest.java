import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;

/**
 * 
 * @author Jacob Smith
 * Tests for the DictionaryModel class
 *
 */
public class DictionaryModelTest {

	DictionaryModel model;
	/**
	 * Tests addCharacter method with adding 4
	 * @throws IOException
	 */
	@Test
	public void test1() throws IOException {
		DictionaryModel model = new DictionaryModel();

		model.currentSig = "545";
		model.addCharacter('4');
		
		assertEquals("5454", model.currentSig);
	}
	/**
	 * Tests addCharacter method with a longer number adding 2
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		DictionaryModel model = new DictionaryModel();

		model.currentSig = "23456";
		model.addCharacter('2');
		
		assertEquals("234562", model.currentSig);
	}
	/**
	 * Tests removeLastCharacter method by removing the last character 4
	 * @throws IOException
	 */
	@Test
	public void test3() throws IOException {
		DictionaryModel model = new DictionaryModel();

		model.currentSig = "5454";
		model.removeLastCharacter();
		
		assertEquals("545", model.currentSig);
	}
	/**
	 * Tests the removeLastCharacter method by removing from more characters
	 * @throws IOException
	 */
	@Test
	public void test4() throws IOException {
		DictionaryModel model = new DictionaryModel();

		model.currentSig = "234567";
		model.removeLastCharacter();
		
		assertEquals("23456", model.currentSig);
	}
	/**
	 * Tests the nextMatch method by cycling to the next ordered match
	 * @throws IOException
	 */
	@Test
	public void test5() throws IOException {
		DictionaryModel model = new DictionaryModel();

		model.match = '0';
		model.nextMatch();
		
		assertEquals('1', model.match);
	}
	/**
	 * Tests the nextMatch method by going 1 match further
	 * @throws IOException
	 */
	@Test
	public void test6() throws IOException {
		DictionaryModel model = new DictionaryModel();

		model.match = '1';
		model.nextMatch();
		
		assertEquals('2', model.match);
	}
	/**
	 * Tests the acceptWord method to see if it accepts the right word in the dictionary at the right location
	 * @throws IOException
	 */
	@Test
	public void test7() throws IOException {

		DictionaryModel model = new DictionaryModel();
		
		model.currentSig = "4663";
		model.match = '0';
		model.acceptWord();
		
		assertEquals(Arrays.asList("gond"),model.currentMsg);
	}
	/**
	 * Tests acceptWord method at its next match with the same signature
	 * @throws IOException
	 */
	@Test
	public void test8() throws IOException {

		DictionaryModel model = new DictionaryModel();
		
		model.currentSig = "4663";
		model.match = '1';
		model.acceptWord();
		
		assertEquals(Arrays.asList("gone"),model.currentMsg);
	}
	/**
	 * Tests another key signature and if it accepts the right word at the right location in the dictionary
	 * @throws IOException
	 */
	@Test
	public void test9() throws IOException {

		DictionaryModel model = new DictionaryModel();
		
		model.currentSig = "329";
		model.match = '0';
		model.acceptWord();
		
		assertEquals(Arrays.asList("daw"),model.currentMsg);
	}
}
