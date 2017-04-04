import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Panel extends JPanel{
	public static TextCompare comparator = new TextCompare();
	public static Vector<Word> wordVector;
  public static int wordVectorIndex = 0;

	JTextField text1;
	JTextField text2;
  JTextField replaceWord;

	JTextPane wordDisplay;
  JTextArea messageDisplay;
  JTextArea statisticsDisplay;
  JScrollPane wordDisplayScroller;

 	public Panel(){
 		//divides app into vertical panels
 		this.setLayout(new GridLayout(2,1));

 		//creates panel in top half
 		JPanel north = new JPanel();
 		north.setLayout(new BorderLayout());

		//left half of top panel
 		JButton addB = new JButton("Add Word");
 		JButton igB = new JButton("Ignore Word");
    JButton prevButton = new JButton("Previous Word");
    JButton nextButton = new JButton("Next Word");

    // Labels for top panel
 		JLabel top = new JLabel("SpellWhiz 2.0");
 		Font font = new Font("Helvetica", Font.BOLD, 24);

    // Style for top panel text
 		top.setFont(font);
 		top.setForeground(Color.blue);

    // Create and add instructions and message display and add to labels panel
 		JTextPane instructions = new JTextPane();
    instructions.setContentType("text/html");
 		String instructionString = "<html><body>";
    instructionString = instructionString + "<b>Type in file names under 'Insert File Names'";
    instructionString = instructionString + "<br>Click 'Upload Files' to upload </br>";
    instructionString = instructionString + "<br>Add/ignore selected word using 'Add' and 'Ignore' buttons</br>";
    instructionString = instructionString + "<br>Use the 'Export Dictionary' button to export to a file </br></b></body></html>";
    instructions.setText(instructionString);
    instructions.setEditable(false);
    instructions.setBackground(new Color(0,0,0,0));

 		JPanel labels = new JPanel();
 		labels.setLayout(new GridLayout(2,1));
    labels.add(instructions);
    messageDisplay = new JTextArea();
    messageDisplay.setEditable(false);
    messageDisplay.setBackground(new Color(0,0,0,0));
    labels.add(messageDisplay);

    // Create statistics panel and add to center display panel
    statisticsDisplay = new JTextArea();
    statisticsDisplay.setEditable(false);
    statisticsDisplay.setText("Statistics: \n");
    JPanel centerDisplay = new JPanel();
    centerDisplay.setLayout(new GridLayout(2,1));
    centerDisplay.add(labels);
    centerDisplay.add(statisticsDisplay);

 		JPanel left = new JPanel();
 		left.setLayout(new BorderLayout());
 		JPanel buttonP = new JPanel();
 		buttonP.setLayout(new GridLayout(2,2));
 		addB.addActionListener(new ButtonListener());
 		igB.addActionListener(new ButtonListener());
 		prevButton.addActionListener(new ButtonListener());
 		nextButton.addActionListener(new ButtonListener());

 		buttonP.add(addB);
 		buttonP.add(igB);
    buttonP.add(prevButton);
    buttonP.add(nextButton);

 		left.add(top, BorderLayout.NORTH);
 		left.add(buttonP,BorderLayout.SOUTH);
 		left.add(centerDisplay, BorderLayout.CENTER);

 		//right half of top panel
		JLabel fileLabel = new JLabel("Insert Filenames");
 		JButton up1 = new JButton("Upload Files");
		text1 = new JTextField("sampleText.txt");
		text2 = new JTextField("testDictionary.txt");

    replaceWord = new JTextField();
    JButton replaceButton = new JButton("Replace");

 		JPanel right = new JPanel();
 		right.setLayout(new BorderLayout());
 		JPanel bP = new JPanel();
    JPanel blankSpace = new JPanel(); // blank panel for padding
 		bP.setLayout(new GridLayout(7,1));
		bP.add(fileLabel);
		bP.add(text1);
		bP.add(text2);
 		bP.add(up1);
    bP.add(blankSpace);
    bP.add(replaceWord);
    bP.add(replaceButton);
 		up1.addActionListener(new ButtonListener());
 		replaceButton.addActionListener(new ButtonListener());
 		right.add(bP, BorderLayout.NORTH);

		//add to top panel
 		north.add(left, BorderLayout.CENTER);
 		north.add(right, BorderLayout.EAST);

		//creates panel in bottom half
		JPanel south = new JPanel();
		south.setLayout(new BorderLayout());
		wordDisplay = new JTextPane();
		wordDisplay.setEditable(false);
    wordDisplay.setContentType("text/html");
    wordDisplayScroller = new JScrollPane(wordDisplay);

		JPanel ex = new JPanel();
		ex.setLayout(new FlowLayout());
		JButton export = new JButton("Export Dictionary");
		export.addActionListener(new ButtonListener());
		ex.add(export);
		south.add(wordDisplayScroller, BorderLayout.CENTER);
		south.add(ex, BorderLayout.SOUTH);

		//add to outermost panel
 		add(north);
 		add(south);
 	}

	 private class ButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				String source = event.getActionCommand();

        // If Upload Files button is pressed, call compareText with the two file names
				if(source.equals("Upload Files")){
					String textFile = text1.getText();
					String dictionaryFile = text2.getText();
					wordVector = comparator.compareText(textFile,dictionaryFile);
				}

        // If Replace button is pressed, replace word with new text
        if(source.equals("Replace")){

        }

        // If Add Word button is pressed, call addWordToDictionary and move on to next word
				if(source.equals("Add Word")){
          comparator.addWordToDictionary(wordVector.get(wordVectorIndex));
          incrementCounter();
				}

        // If Ignore Word button is pressed, call ignoreWord and move on to next word
				if(source.equals("Ignore Word")){
          comparator.ignoreWord(wordVector.get(wordVectorIndex));
          incrementCounter();
				}

        // If Export Dictionary Button is pressed, call writeDictionary and display message
				if(source.equals("Export Dictionary"))
				{
          messageDisplay.setText("\n" + comparator.writeDictionary());
				}

        // If Previous Word button is pressed, decrement the global counter
        if(source.equals("Previous Word")){
          decrementCounter();
        }

        // If Next Word button is pressed, increment the global counter
        if(source.equals("Next Word")){
          incrementCounter();
        }
      updateUIElements();
			}
	 }

  public void updateUIElements(){
    updateTextArea();
  }

  public void updateTextArea(){
    String currentWord = "";
    String wordHTML =  "";
    for(int index = 0; index < wordVector.size(); index++){
      currentWord = wordVector.get(index).text;
      if(index == wordVectorIndex){
        currentWord = "<b>{" + currentWord + "}</b>";
      }
      if(wordVector.get(index).isInDictionary == true){
        wordHTML = wordHTML + "<br><font color=\"green\">" + currentWord + "</font></br>";
      }
      else{
        wordHTML = wordHTML + "<br><font color =\"red\">" + currentWord + "</font></br>";
      }
    }
  wordDisplay.setText("<html><body>" + wordHTML + "</body></html>");
  System.out.println("Global index: " + wordVectorIndex + "\n");
  }

  public void incrementCounter(){
    wordVectorIndex ++;
    if(wordVectorIndex > wordVector.size()-1){
      wordVectorIndex = wordVector.size() - 1;
    }
  }

  public void decrementCounter(){
    wordVectorIndex--;
    if(wordVectorIndex < 0){
      wordVectorIndex = 0;
    }
  }
}
