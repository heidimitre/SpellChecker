import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Panel extends JPanel
 {
	public static TextCompare comparator = new TextCompare();
	public static Vector<Word> wordVector;
	JButton up1;
	JTextField text1;
	JTextField text2;
	JTextField currentWord;
 
 	public Panel()
 	{
 		//divides app into vertical panels
 		this.setLayout(new GridLayout(2,1));

 		//creates panel in top half
 		JPanel north = new JPanel();
 		north.setLayout(new BorderLayout());

		//left half of top panel
 		JButton addB = new JButton("Add Word");
 		JButton igB = new JButton("Ignore Word");
 		JLabel top = new JLabel("SpellWhiz 2.0");
 		Font font = new Font("Helvetica", Font.BOLD, 24);
 		JLabel inst1 = new JLabel("Type in file names under 'Insert File Names'");
 		JLabel inst2 = new JLabel("Click 'Upload Files' to upload");
 		JLabel inst3 = new JLabel("Use the 'Add' and 'Ignore' buttons to edit word list");
 		JLabel inst4= new JLabel("Use the 'Export Dictionary' button to export to a file");
 		top.setFont(font);
 		top.setForeground(Color.blue);
 		inst1.setForeground(Color.red);
 		inst2.setForeground(Color.red);
 		inst3.setForeground(Color.red);
 		inst4.setForeground(Color.red);
 		JPanel labels = new JPanel();
 		labels.setLayout(new GridLayout(4,1));
 		labels.add(inst1);
 		labels.add(inst2);
 		labels.add(inst3);
 		labels.add(inst4);
 		JPanel left = new JPanel();
 		left.setLayout(new BorderLayout());
 		JPanel buttonP = new JPanel();
 		buttonP.setLayout(new FlowLayout());
 		buttonP.add(addB);
 		addB.addActionListener(new ButtonListener());
 		igB.addActionListener(new ButtonListener());
 		buttonP.add(igB);
 		left.add(top, BorderLayout.NORTH);
 		left.add(buttonP,BorderLayout.SOUTH);
 		left.add(labels, BorderLayout.CENTER);


 		//right half of top panel
		JLabel fileLabel = new JLabel("Insert Filenames");
 		up1 = new JButton("Upload Files");
		text1 = new JTextField("sampleText.txt");
		text2 = new JTextField("testDictionary.txt");

 		JPanel right = new JPanel();
 		right.setLayout(new BorderLayout());
 		JPanel bP = new JPanel();
 		bP.setLayout(new GridLayout(4,1));
		bP.add(fileLabel);
		bP.add(text1);
		bP.add(text2);
 		bP.add(up1);
 		up1.addActionListener(new ButtonListener());
 		right.add(bP, BorderLayout.NORTH);

 		//center of top panel (empty)
 		JPanel center = new JPanel();

		//add to top panel
 		north.add(left, BorderLayout.WEST);
 		north.add(center, BorderLayout.CENTER);
 		north.add(right, BorderLayout.EAST);

		//creates panel in bottom half
		JPanel south = new JPanel();
		south.setLayout(new BorderLayout());
		JLabel title = new JLabel("Add/Ignore Word");
		currentWord = new JTextField();
		currentWord.setEditable(false);

		JPanel ex = new JPanel();
		ex.setLayout(new FlowLayout());
		JButton export = new JButton("Export Dictionary");
		export.addActionListener(new ButtonListener());
		ex.add(export);
		south.add(title, BorderLayout.NORTH);
		south.add(currentWord, BorderLayout.CENTER);
		south.add(ex, BorderLayout.SOUTH);

		//add to outermost panel
 		add(north);
 		add(south);
 	}
 
	 private class ButtonListener implements ActionListener
	 {
			public void actionPerformed(ActionEvent event){
				String source = event.getActionCommand();
				if(source.equals("Upload Files"))
				{
					String textFile = text1.getText();
					String dictionaryFile = text2.getText();		
					wordVector = comparator.compareText(textFile,dictionaryFile);
					//comparator.printWords();
					updateUIElements();
				}
				if(source.equals("Add Word"))
				{
					 boolean doneSearching = false;
				      int index = 0;
				      while(index < wordVector.size() && doneSearching == false){
				        if(wordVector.get(index).isFound == false){
				          wordVector.get(index).isFound = true;
				          doneSearching = true;
				          comparator.addWordToDictionary(wordVector.get(index));
				        }
				        index++;
				      }
				      updateUIElements();
				}
				if(source.equals("Ignore Word"))
				{
					boolean doneSearching = false;
				    int index = 0;
				    while(index < wordVector.size() && doneSearching == false){
				    	if(wordVector.get(index).isFound == false)
				    	{
				    		wordVector.get(index).isFound = true;
				    		doneSearching = true;
				    		comparator.ignoreWord(wordVector.get(index));
				        }
				        index++;
				      }
				    updateUIElements();
				 }
				if(source.equals("Export Dictionary"))
				{
					comparator.writeDictionary();
				}

			}
	 }
	 
	 public void updateUIElements(){
		    boolean doneSearching = false;
		    int index = 0;
		    while(index < wordVector.size() && doneSearching == false){
		      if(wordVector.get(index).isFound == false){
		        doneSearching = true;
		        currentWord.setText(wordVector.get(index).text);
		      }
		      index++;
		    }
		    if(doneSearching == false){
		      currentWord.setText("All words in file have been checked!");
		      comparator.printDictionaryWords();
		    }
		  }
 }
