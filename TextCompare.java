import java.io.File;
import java.util.Scanner;

public class TextCompare{
	public static void main(String[] args){

		
	    String fileName = "testDictionary.txt";
	    String readLine = "";
	    File file = new File(fileName);
	
	    try{
	      Scanner scanner = new Scanner(file);
	
	      while(scanner.hasNextLine()){
	        readLine = scanner.nextLine();
	      }
	
	      scanner.close();
	    }
	    catch(Exception ex){
	      System.out.println("Error reading file.");
	    }
	}
}
