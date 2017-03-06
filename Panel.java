import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Panel extends JPanel
 {
  	JButton up1;
	JTextField text1;
	JTextField text2;
	JList words;
	TextCompare comparator = new TextCompare();
 
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
 		JPanel left = new JPanel();
 		left.setLayout(new BorderLayout());
 		JPanel buttonP = new JPanel();
 		buttonP.setLayout(new FlowLayout());
 		buttonP.add(addB);
 		buttonP.add(igB);
 		left.add(top, BorderLayout.NORTH);
 		left.add(buttonP,BorderLayout.SOUTH);


 		//right half of top panel
		JLabel fileLabel = new JLabel("Insert Filenames");
 		up1 = new JButton("Upload Files");
		text1 = new JTextField("sampleText.txt");
		text2 = new JTextField("testDictionary.txt");
 		JButton save = new JButton("Save All");
 		JPanel right = new JPanel();
 		right.setLayout(new BorderLayout());
 		JPanel bP = new JPanel();
 		bP.setLayout(new GridLayout(4,1));
		bP.add(fileLabel);
		bP.add(text1);
		bP.add(text2);
 		bP.add(up1);
 		right.add(bP, BorderLayout.NORTH);
 		right.add(save, BorderLayout.SOUTH);

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
		JTextField list = new JTextField();

		JPanel ex = new JPanel();
		ex.setLayout(new FlowLayout());
		JButton export = new JButton("Export Dictionary");
		ex.add(export);
		south.add(title, BorderLayout.NORTH);
		south.add(list, BorderLayout.CENTER);
		south.add(ex, BorderLayout.SOUTH);
		//*********vector is input of JList!!!************
		words = new JList();
		JScrollPane scr = new JScrollPane(words);

		//add to outermost panel
 		add(north);
 		add(south);
 	}

	public void actionPerformed(ActionEvent event){
		String source = event.getActionCommand();
		
		text1.setText(source);
		if(source.equals("Upload Text File")){
			String textFile = text1.getText();
			String dictionaryFile = text2.getText();		
			comparator.compareText(textFile,dictionaryFile);
		}
	}
 }
