import java.io.File;
import java.util.*;

public class TextCompare{

  public Vector <Word> wordCompareVector;
  public Vector <String> missingWordsVector;

  public TextCompare(){
    wordCompareVector = new Vector <Word> ();
    missingWordsVector = new Vector <String> ();
  }

  public Vector <String> compareText(String textFile, String dictionaryFile){
    readFile(textFile);
    readDictionary(dictionaryFile);
    addMissingWords();
    return missingWordsVector;
  }

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
            if(!foundWords[index].equals("") && !wordExists(foundWords[index])){
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
  public boolean wordExists(String checkWord){
    boolean wordFound = false;
    for(Word word : wordCompareVector){
      if(checkWord.equals(word.text)){
        wordFound = true;
      }
    }
    return wordFound;
  }

  // Compare words in wordCompareVector with dictionary words
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

          // Check if the word is not an empty String
          // Then mark all words found in wordCompareVector
          if(!foundWords[index].equals("")){
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

  public void addMissingWords(){
    for(Word word : wordCompareVector){
      if(word.isFound == false){
       missingWordsVector.add(word.text);
      }
    }
  }

  public void printWords(){
    System.out.println(wordCompareVector.size());
    for(Word word : wordCompareVector){
      System.out.println(word.text + " " + word.isFound);
    }
  }

  public void printMissingWords(){
    System.out.println("Missing words:");
    for(String word : missingWordsVector){
      System.out.println(word);
    }
  }
}
