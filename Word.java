/**
 * Word class for managing Word objects created from reading a text file.
 * @author heidimitre
 *
 */
public class Word{
	/**
	 * Word objects have two attributes:
	 * 1) a String that contains the text of a Word
	 * 2) a boolean that indicates if the word was found in the dictionary
	 */
	String text;
	boolean isInDictionary;

	/**
	 * The Word constructor creates a new Word object given a String
	 * containing the Word's text
	 * @param text the text of the word read from the file
	 * isInDictionary is initially set to false
	 */
	public Word(String text){
		this.text = text;
		isInDictionary = false;
	}

  public String toString(){
    return text;
  }
}
