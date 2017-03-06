import javax.swing.*;
import java.util.*;

public class App extends JApplet
 {
   private int APPLET_WIDTH = 900, APPLET_HEIGHT = 350;

   //private CreatePanel createPanel;
   //private ProjectSpendingPanel spendingPanel;
   //private Vector projectList;

   //The method init initializes the Applet
   public void init()
    {
    	Panel panel = new Panel();
    	getContentPane().add(panel);

    	setSize(800, 400);

    }
}
