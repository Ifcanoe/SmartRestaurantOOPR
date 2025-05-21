import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

  private RoundedButtonS startOrderB = new RoundedButtonS("START ORDER");
  private JLabel smartDiningRestaurantTitle = new JLabel();
  private JLabel logoLabel = new JLabel();
  private ImageIcon logoIcon = new ImageIcon(ImageUtilities.class.getResource("/images/icons/logo.png"));
  
  private GridBagConstraints gbc = new GridBagConstraints();
  private GridBagLayout startPanelLayout = new GridBagLayout();

  StartPanel(Controller mvc){
    setLayout(startPanelLayout);
    setBackground(UIUtilities.CREAM);
    
    // startOrderB.setPreferredSize(new Dimension(200, 100));

    smartDiningRestaurantTitle.setText("SMART DINING RESTAURANT");
    smartDiningRestaurantTitle.setFont(UIUtilities.baseFont.deriveFont(Font.PLAIN, 32f));

    logoLabel.setIcon(logoIcon);

    startOrderB.setBorder(null);
    startOrderB.setForeground(UIUtilities.CREAM);
    startOrderB.setBackground(UIUtilities.RESTO_BROWN);
    startOrderB.addActionListener(e -> {
      mvc.switchPanel("MenuP");
      mvc.displayBars(true);
      mvc.modelCreateNewOrder();
    });
    
    gbc.weightx = 0.5;
    gbc.insets = new Insets(30, 10, 0, 10);

    GridBagUtilities.addObject(smartDiningRestaurantTitle, this, startPanelLayout, gbc, 0, 0, 1, 1);
    GridBagUtilities.addObject(logoLabel, this, startPanelLayout, gbc, 1, 0, 1, 1);

    gbc.insets = new Insets(20, 0, 30, 0);
    gbc.ipadx = 30;
    gbc.ipady = 30;
    GridBagUtilities.addObject(startOrderB, this, startPanelLayout, gbc, 2, 0, 1, 1);

  }

}