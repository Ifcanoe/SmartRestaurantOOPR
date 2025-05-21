package GUI;
import javax.swing.*;
import javax.swing.border.*;

import Utilities.GridBagUtilities;
import Utilities.ImageUtilities;
import Utilities.UIUtilities;

import java.awt.*;

public class BottomBar extends JPanel {
  private JLabel totalLabel = new JLabel();
  private JTextField totalTextField = new JTextField();
  private JLabel cartLabel = new JLabel();
  private JLabel currencySignLabel = new JLabel("₱");
  private GridBagLayout bottomBarLayout = new GridBagLayout();
  private GridBagConstraints gbc = new GridBagConstraints();

  public void setTotalTextField(float total){
    if (total > 0){
      totalTextField.setText(Float.toString(total));
    } else {
      totalTextField.setText(null);
    }
    
    totalTextField.revalidate();
    totalTextField.repaint();
  }

  public float getTotal(){
    return Float.parseFloat((totalTextField.getText()));
  }

  BottomBar(Controller mvc){

    setBackground(UIUtilities.DARK_GREEN);
    setLayout(bottomBarLayout);
    setBorder(new LineBorder(new Color(220, 220, 220), 5));

    // TotalLabel Settings
    totalLabel.setText("TOTAL: ");
    totalLabel.setForeground(UIUtilities.CREAM);
    totalLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Currency Sign Label Settings
    currencySignLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    currencySignLabel.setForeground(UIUtilities.CREAM);
    UIUtilities.setFontSize(currencySignLabel, 20f);

    // Total Text Field Settings
    totalTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
    totalTextField.setSize(100, 100);
    totalTextField.setHorizontalAlignment(SwingConstants.CENTER);
    totalTextField.setFont(UIUtilities.baseFont.deriveFont(Font.PLAIN, 24f));
    totalTextField.setForeground(UIUtilities.RESTO_GRAY);
    totalTextField.setEditable(false);

    // Cart Label Settings
    cartLabel.setHorizontalAlignment(SwingConstants.CENTER);
    cartLabel.setIcon(ImageUtilities.getImage("/images/icons/cart1.png", 50, 50));
    
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 0.1/0.3;
    gbc.insets = new Insets(0, 10, 0, 10);

    GridBagUtilities.addObject(totalLabel, this, bottomBarLayout, gbc, 0, 0, 1, 1);

    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.1;
    GridBagUtilities.addObject(currencySignLabel, this, bottomBarLayout, gbc, 0, 1, 1, 1);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    gbc.ipady = 40;
    GridBagUtilities.addObject(totalTextField, this, bottomBarLayout, gbc, 0, 2, 1, 1);

    gbc.weightx = 0.1/0.3;
    gbc.insets = new Insets(0, 10, 0, 10);
    GridBagUtilities.addObject(cartLabel, this, bottomBarLayout, gbc, 0, 3, 1, 1);

  }
}
