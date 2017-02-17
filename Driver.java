import java.util.*;

public class Driver{
	public static void main(String[] args){

	// Create new TextCompare object and String Vector to hold missing words
	TextCompare comparator =  new TextCompare();
	Vector <Word> compareWords = comparator.compareText("sampleText.txt", "scrambledDictionary.txt");

	comparator.printWords();
	comparator.printDictionaryWords();
	}
}