import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * TextCompare class manages TextCompare objects which hold the vectors of Word and DictionaryWord objects
 * @author heidimitre
 *
 */
public class TextCompare{
	/**
	 * TextCompare objects have two attributes:
	 * 1) a wordCompareVector which contains the Word objects read in from a text file
	 * 2) a dictionaryWordVector which contains the DictionaryWord objects read in from a dictionary file
	 */
	public Vector <Word> wordCompareVector;
	public Vector <DictionaryWord> dictionaryWordVector;

	/**
	 * The TextCompare constructor creates a new TextCompare object with empty wordCompareVector and dictionaryWordVector
	 */
	public TextCompare(){
		wordCompareVector = new Vector <Word> ();
		dictionaryWordVector = new Vector <DictionaryWord> ();
	}

	/**
	 * The compare Text method calls the readFile method to read a text file
	 * and calls the readDictionary method to read a dictionary file.
	 * @param textFile the name of the text file to be read
	 * @param dictionaryFile the name of the dictionary file to be read
	 * @return the vector containing all Word objects read from the text file
	 */
	public Vector <Word> compareText(String textFile, String dictionaryFile){
		readFile(textFile);
		readDictionary(dictionaryFile);
		return wordCompareVector;
	}

	/**
	 * The readFile method reads the text file and adds a new Word object for each word to the wordCompareVector.
	 *
	 * The readFile method checks for duplicate words by calling the isDuplicate method.
	 *
	 * @param fileName the name of the text file to read
	 */
	public void readFile(String fileName){
		String readLine = "";
		File file = new File(fileName);
		String [] foundWords;

		try{
			Scanner scanner = new Scanner(file);

			// Store next line in readLine until end of file reached
			while(scanner.hasNextLine()){
				readLine = scanner.nextLine();

				// Separate strings by spaces and convert to lower case
				readLine = readLine.toLowerCase();
				foundWords = readLine.split(" ");

				// Create array of all words on line and remove all non-letter characters
				for (int index = 0; index < foundWords.length; index++){
					foundWords[index] = foundWords[index].replaceAll("[^a-z]", "");

					// Check if string is empty and not already in vector before creating a word
					if(!foundWords[index].equals("") && !isDuplicate(foundWords[index])){
						wordCompareVector.add(new Word(foundWords[index]));
					}
				}
			}
			scanner.close();
		}
		catch(Exception ex){
			System.out.println("Error reading file.");
		}
	}

	/**
	 * The isDuplicate method searches each Word in wordCompareVector to determine if there is already a Word
	 * object for the given string
	 * @param checkWord is the String that will be searched in wordCompareVector
	 * @return a boolean that indicates if word is a duplicate
	 */
	// Return true if word in vector, false if not in vector
	public boolean isDuplicate(String checkWord){
		boolean wordFound = false;
		for(Word word : wordCompareVector){
			if(checkWord.equals(word.text)){
				wordFound = true;
			}
		}
		return wordFound;
	}

	/**
	 * The readDictionary method reads the dictionary file and adds a new DictionaryWord object
	 * for each dictionary word to the dictionaryWordVector.
	 *
	 * The readDictionary method also calls the markAsFound method.
	 *
	 * @param fileName the name of the dictionary file to read
	 */
	// Read dictionary words into dictionaryWordVector
	public void readDictionary(String fileName){
		String readLine = "";
		File file = new File(fileName);
		String [] foundWords;

		try{
			Scanner scanner = new Scanner(file);

			// Store next line in readLine until end of file reached
			while(scanner.hasNextLine()){
				readLine = scanner.nextLine();

				// Separate strings by spaces and convert to lower case
				readLine = readLine.toLowerCase();
				foundWords = readLine.split(" ");

				// Create array of all words on line and remove all non-letter characters
				for (int index = 0; index < foundWords.length; index++){
					foundWords[index] = foundWords[index].replaceAll("[^a-z]", "");

					// Check if string is empty before creating a dictionaryWord
					// Then mark all words found in wordCompareVector
					if(!foundWords[index].equals("") && !inDictionary(foundWords[index])){
						dictionaryWordVector.add(new DictionaryWord(foundWords[index]));
						markAsFound(foundWords[index]);
					}
				}
			}
			scanner.close();
		}
		catch(Exception ex){
			System.out.println("Error reading file.");
		}
	}

	/**
	 * The markAsFound method sets isInDictionary to true for all Word objects in wordCompareVector if
	 * the Word is found in the dictionary file.
	 * @param dictionaryWord the word read from the dictionary
	 */
	public void markAsFound(String dictionaryWord){
		for(Word word : wordCompareVector){
			if(dictionaryWord.equals(word.text)){
				word.isInDictionary = true;
			}
		}
	}

	/**
	 * The inDictionary method searches each DictionaryWord in dictionaryWordVector to determine if there is already
	 * a DictionaryWord object for the given string
	 * @param checkWord is the String that will be searched in dictionaryWordVector
	 * @return a boolean that indicates if word is a duplicate
	 */
	// Return true if word in vector, false if not in vector
	public boolean inDictionary(String checkWord){
		boolean wordFound = false;
		for(DictionaryWord word : dictionaryWordVector){
			if(checkWord.equals(word.text)){
				wordFound = true;
			}
		}
		return wordFound;
	}

	/**
	 * The printWords method is used for testing and prints all Word objects in the wordCompareVector.
	 */
	public void printWords(){
		System.out.println(wordCompareVector.size());
		for(Word word : wordCompareVector){
			System.out.println(word.text + " " + word.isInDictionary);
		}
	}

	/**
	 * The printDictionaryWords method is used for testing and prints all DictionaryWord objects in the dictionaryWordVector.
	 */
	public void printDictionaryWords(){
		System.out.println(dictionaryWordVector.size());
		for(DictionaryWord word : dictionaryWordVector){
			System.out.println(word.text + " " + word.isVisible);
		}
	}

	/**
	 * The addWordToDictionary method adds a word to the dictionaryWordVector as visible
	 *
	 * @param addThis is the Word being added as visible
	 */
	public void addWordToDictionary(Word addThis){
		String word = addThis.text; //save text
		addThis.isInDictionary = true; //change boolean of word to isInDictionary = true
    if(!inDictionary(addThis.text)){
      DictionaryWord addedWord = new DictionaryWord(word); //create new dictionary word with the same string as word given
      dictionaryWordVector.add(addedWord); //add new dictionary word to dictionary vector
    }
  }

	/**
	 * The ignoreWord method adds a word to the dictionaryWordVector as ignored
	 *
	 * @param ignoreThis is the Word being added as ignored
	 */
	public void ignoreWord(Word ignoreThis){
		String word = ignoreThis.text; //save text
		ignoreThis.isInDictionary = true; //change boolean of word to isInDictionary = true
		DictionaryWord ignoredWord = new DictionaryWord(word); //create new dictionary word with the same string as word given
		ignoredWord.isVisible = false; //specify that it is ignored through isVisible = false
		dictionaryWordVector.add(ignoredWord); //add new dictionary word to dictionary vector


	}

	/**
	 * The writeDictionary method creates a dictionary file and writes to it all the String text of each
	 * visible DictionaryWord within the dictionaryWordVector.
	 *
	 */
	public void writeDictionary(){
		FileOutputStream fop = null;
		File file;

		String content = "";
		for(DictionaryWord word: dictionaryWordVector){
			if (word.isVisible){
				content = content + word.text +"\n";
			}
		}

		try {

			file = new File("dictionary.txt");
			fop = new FileOutputStream(file);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			System.out.println("File successfully written!");

		}
		catch(Exception ex){
			System.out.println("Error writing file."+ex.toString());
		}
	}
}
