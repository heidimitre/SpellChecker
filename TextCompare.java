import java.io.File;
import java.util.*;

public class TextCompare{

	public Vector <Word> wordCompareVector;
	public Vector <DictionaryWord> dictionaryWordVector;

	public TextCompare(){
		wordCompareVector = new Vector <Word> ();
		dictionaryWordVector = new Vector <DictionaryWord> ();
	}

	public Vector <Word> compareText(String textFile, String dictionaryFile){
		readFile(textFile);
		readDictionary(dictionaryFile);
		return wordCompareVector;
	}

	//Read words into wordCompareVector
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

	// Check if word is already in wordCompareVector
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
					if(!foundWords[index].equals("")){
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

  public void markAsFound(String dictionaryWord){
    for(Word word : wordCompareVector){
      if(dictionaryWord.equals(word.text)){
        word.isFound = true;
      }
    }
  }

	public void printWords(){
		System.out.println(wordCompareVector.size());
		for(Word word : wordCompareVector){
			System.out.println(word.text + " " + word.isFound);
		}
	}

	public void printDictionaryWords(){
		System.out.println(dictionaryWordVector.size());
		for(DictionaryWord word : dictionaryWordVector){
			System.out.println(word.text + " " + word.isVisible);
		}
	}
}
