import javax.swing.*;
import java.awt.*;

public class RestoGUI extends JFrame {
  JFrame mainFrame;
  JPanel mainPanel;
  GridBagLayout mainGrid;
  GridBagConstraints gbc;
  JButton button1;
  JButton button2;

  public void addobject(Component componente, Container yourcontainer, GridBagLayout layout, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight){
    gbc.gridx = gridx;
    gbc.gridy = gridy;

    gbc.gridwidth = gridwidth;
    gbc.gridheight = gridheight;

    layout.setConstraints(componente, gbc);
    yourcontainer.add(componente);

  }

  RestoGUI() {
    // Instantiate the objects
    mainFrame = new JFrame();
    mainPanel = new JPanel();
    mainGrid = new GridBagLayout();
    gbc = new GridBagConstraints();
    button1 = new JButton("Click me");
    button2 = new JButton("No! Click me!");

    // Main Frame settings
    mainFrame.setTitle("I am a window");
    mainFrame.setSize(100, 100);
    mainFrame.setVisible(true);
    mainFrame.add(mainPanel);

    // Main Panel settings
    mainPanel.setLayout(mainGrid);
    addobject(button1, mainPanel, mainGrid, gbc, 0, 0, 1, 1);
    addobject(button2, mainPanel, mainGrid, gbc, 1, 0, 1, 1);



  }

  public static void main(String[] args) {
    new RestoGUI();
  }
}