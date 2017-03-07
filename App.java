import javax.swing.*;
import java.util.*;

public class App extends JFrame{

   public static void main(String [] args){
     JFrame frame = new App();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(600, 400);
      frame.setVisible(true);
    }

   public App(){
    	Panel panel = new Panel();
      this.add(panel);
   }
}
