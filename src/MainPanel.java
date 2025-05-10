package RestoGUI.SmartRestaurantOOPR.src;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JFrame {
  MainPanel(){
    setTitle("Test Window");
    setSize(400, 400);
    add(new JLabel("Hello"));
    setVisible(true);
  }

  public static void main(String[] args){
    new MainPanel();
  }
}
