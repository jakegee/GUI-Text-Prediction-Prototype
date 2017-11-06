
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
/**
 * 
 * @author Jacob Smith
 * In this class we're utilising the previous methods in our dictionary classes (using SampleDictionary 
 * in this particular case) in order to have a functioning old t9 GUI
 *
 */
public class DictionaryModel extends Observable implements DictionaryModelInterface {

	SampleDictionary dictionary; // Initialises the SampleDictionary
	List<String> currentMsg = new ArrayList<String>(); // User messages
	String currentSig = ""; // Key input
	int match; // Selects which word to be used from the dictionary
	
	/**
	 * This constructor takes the path of the dictionary file and initialises its data
	 * @param dictionaryFile
	 * @throws java.io.IOException
	 */
	public DictionaryModel(String dictionaryFile) throws java.io.IOException {
		this.dictionary = new SampleDictionary(dictionaryFile);		
	}
	/**
	 * This second constructor uses the dictionary file that was provided
	 * @throws java.io.IOException
	 */
	public DictionaryModel() throws java.io.IOException {
		this.dictionary = new SampleDictionary("words");
	}
	/**
	 * Returns a list of the words typed 
	 */
	@Override
	public List<String> getMessage() {
			return currentMsg;
	}
	/**
	 * Adds a numeric key that as been typed
	 */
	@Override
	public void addCharacter(char key) {
			currentSig += String.valueOf(key);
			System.out.print(key);
	}
	/**
	 * Removes the last character typed
	 */
	@Override
	public void removeLastCharacter() {
		this.match = 0;
			if (currentSig.length()!=0) {
				currentSig = currentSig.substring(0, currentSig.length()-1);
			} else {
				return;
			}
	}
	/**
	 * Cycles through the possible match for the current word
	 */
	@Override
	public void nextMatch() {
		match += 1;
		int temp = dictionary.signatureToWords(currentSig).size();
		System.out.print(match % temp);
	}
	/**
	 * Accepts the current matched word and adds it to the composed message
	 */
	@Override
	public void acceptWord() {
		ArrayList<String> possibleWord = new ArrayList<String>();
		
		if (!currentSig.equals("")) { // Purpose of this statement is to check for a blank
			for (String input : dictionary.signatureToWords(currentSig)) { // for each loop to check through the dictionary
				possibleWord.add(input);
		}
		if (possibleWord.size() > 0) { // Must have a size greater than 0 or we wouldn't be searching for anything
			match = match % (possibleWord.size());
			currentMsg.add(possibleWord.get(match));
		} else {
			System.out.println("Try a differnet signature"); // Message to alert the user that key combo doesn't work
		}
		
		setChanged(); // Necessary to add as we need to mark the object as having been changed
		notifyObservers(); // Necessary to let observers know the object has been changed and to indicate it has been cleared
		
		currentSig = "";
		}
	}
}
