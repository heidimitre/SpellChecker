/**
 * DictionaryWord class manages DictionaryWord objects created from reading a dictionary file
 * @author heidimitre
 *
 */
public class DictionaryWord{
	/**
	 * DictionaryWord objects have two attributes:
	 * 1) a String that contains the text of a DictionaryWord
	 * 2) a boolean that indicates if the word is visible in the dictionary (true) or was ignored by the user (false)
	 */
	String text;
	boolean isVisible;
  
	/**
	 * The DictionaryWord constructor creates a new DictionaryWord object given a String
	 * containing the DictionaryWord's text
	 * @param text the text of the dictionary word read from the file
	 * isVisible is initially set to true
	 */
	public DictionaryWord(String text){
		this.text = text;
		isVisible = true;
	}
}
