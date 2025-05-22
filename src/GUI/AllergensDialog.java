package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Utilities.GridBagUtilities;
import Utilities.UIUtilities;

public class AllergensDialog extends JDialog {
  
  private ArrayList<JCheckBox> allergenList;

  
  public void displayDialog(){
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    
  }
  
  public void addAllergenItem(ArrayList<String> allergens){
    
    allergensPanel.removeAll();

    for (String allergen : allergens){
      if (allergen == null){
        return;
      }
      
      JCheckBox allergenCheckBox = new JCheckBox(allergen);
      allergenCheckBox.setBackground(null);
      allergenCheckBox.setFocusable(false);
      allergenList.add(allergenCheckBox);
      allergensPanel.add(allergenCheckBox);
    }
    
    allergensPanel.revalidate();
    allergensPanel.repaint();
  }
  
  public ArrayList<String> getSelectedAllergens(){
    ArrayList<String> selectedAllergens = new ArrayList<>();
    

    for (JCheckBox cb : allergenList){
      if (cb.isSelected()){
        selectedAllergens.add(cb.getText());
      }
    }


    return selectedAllergens;  
  }

  public void resetSelected(){
    for (JCheckBox cb : allergenList){
      cb.setSelected(false);
    }
  }
  
  
  private JPanel buttonsPanel = new JPanel();
  private JButton confirmAllergensButton = new JButton();
  private JButton cancelAllergensButton = new JButton();
  private JPanel allergensPanel;
  private JLabel allergenInquiry = new JLabel();
  
  private Controller mvc;
  
  AllergensDialog(Controller mvc){
    setUndecorated(true);
    setPreferredSize(new Dimension(500, 220));

    //* Allergen List Settings
    allergenList = new ArrayList<>();

    
    //* Allergens Panel Settings
    allergensPanel = new JPanel();
    allergensPanel.setAlignmentX(CENTER_ALIGNMENT);

    //* Allergen Inquiry Settings
    allergenInquiry.setAlignmentX(CENTER_ALIGNMENT);
    allergenInquiry.setText("SELECT ANY ALLERGIES OR DIETARY RESTRICTIONS");
    
    //* Cancel Allergens Button Settings
    cancelAllergensButton.setAlignmentX(CENTER_ALIGNMENT);
    cancelAllergensButton.setForeground(UIUtilities.CREAM);
    cancelAllergensButton.setBackground(UIUtilities.DARK_GREEN);
    cancelAllergensButton.setText("CANCEL");
    cancelAllergensButton.addActionListener(e -> {
      resetSelected();
      mvc.displayCategory("MainDish");
      dispose();
    });
    
    //* Confirm Allergens Button
    confirmAllergensButton.setAlignmentX(CENTER_ALIGNMENT);
    confirmAllergensButton.setForeground(UIUtilities.CREAM);
    confirmAllergensButton.setBackground(UIUtilities.DARK_GREEN);
    confirmAllergensButton.setText("CONFIRM ALLERGENS?");
    confirmAllergensButton.addActionListener(e -> {
      mvc.displayCategory("MainDish");
      dispose();
    });
    
    
    //* Buttons Panel Settings
    buttonsPanel.add(cancelAllergensButton);
    buttonsPanel.add(confirmAllergensButton);

    //* Create on construct
    Container allergensPane = getContentPane();
    allergensPane.setLayout(new BoxLayout(allergensPane, BoxLayout.PAGE_AXIS));

    //? Typecast Container into JComponent to paint border
    ((JComponent) allergensPane).setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.GRAY, 2), new EmptyBorder(5, 5, 5, 5)));

    allergensPane.add(allergenInquiry);
    allergensPane.add(allergensPanel);
    allergensPane.add(buttonsPanel);

    pack();

    
    


  }

}
