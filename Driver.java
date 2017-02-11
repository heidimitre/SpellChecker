import java.util.*;

public class Driver{
  public static void main(String[] args){

    // Create new TextCompare object and String Vector to hold missing words
    TextCompare comparator =  new TextCompare();
    Vector <String> missingWords = comparator.compareText("sampleText.txt", "testDictionary.txt");

    comparator.printMissingWords();
  }
}
