package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Utilities.GridBagUtilities;
import Utilities.ImageUtilities;
import Utilities.UIUtilities;

public class CheckoutPanel extends JPanel {
  
  private GridBagLayout checkoutPanelLayout = new GridBagLayout();
  private GridBagConstraints gbc = new GridBagConstraints();

  private JPanel dineInPanel = new JPanel();
  private JPanel takeoutPanel = new JPanel();

  private JLabel dineInLabel = new JLabel();
  private JLabel takeoutLabel = new JLabel();

  private JLabel dineInImage = new JLabel();
  private JLabel takeoutImage = new JLabel();

  private final Color DARK_GREEN = new Color(64, 97, 55);
  private final Color CLICKED_DARK_GREEN = new Color(84, 126, 71);
  
  private Controller mvc;
  
  CheckoutPanel(Controller mvc){
    this.mvc = mvc;
    setLayout(checkoutPanelLayout);
    // setBackground(UIUtilities.CREAM);

    //* Dine In Label
    dineInLabel.setText("DINE IN");
    dineInLabel.setForeground(UIUtilities.CREAM);
    dineInLabel.setAlignmentX(CENTER_ALIGNMENT);

    //* Takeout Label
    takeoutLabel.setText("TAKEOUT");
    takeoutLabel.setForeground(UIUtilities.CREAM);
    takeoutLabel.setAlignmentX(CENTER_ALIGNMENT);

    //* Dine In Image
    dineInImage.setIcon(ImageUtilities.getImage("/images/icons/dinein.png"));
    dineInImage.setAlignmentX(CENTER_ALIGNMENT);

    //* Takeout Image
    takeoutImage.setIcon(ImageUtilities.getImage("/images/icons/takeout.png"));
    takeoutImage.setAlignmentX(CENTER_ALIGNMENT);

    //* Dine In Panel
    dineInPanel.setLayout(new BoxLayout(dineInPanel, BoxLayout.PAGE_AXIS));
    dineInPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
    dineInPanel.setPreferredSize(new Dimension(257, 301));
    dineInPanel.setBackground(DARK_GREEN);
    UIUtilities.setFontSize(dineInLabel, 20f);
    dineInPanel.add(dineInImage);
    dineInPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    dineInPanel.add(dineInLabel);

    dineInPanel.addMouseListener(new MouseAdapter(){
      @Override
      public void mouseReleased(java.awt.event.MouseEvent e) {
        System.out.print("Hello");
        dineInPanel.setBackground(DARK_GREEN);
      }

      @Override
      public void mousePressed(MouseEvent e) {
        dineInPanel.setBackground(CLICKED_DARK_GREEN);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        dineInPanel.setBackground(DARK_GREEN);
      }

    });
    

    //! System.out.print(dineInPanel.getPreferredSize());
    
    //* Takeout Panel
    takeoutPanel.setLayout(new BoxLayout(takeoutPanel, BoxLayout.PAGE_AXIS));
    takeoutPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
    takeoutPanel.setBackground(DARK_GREEN);
    takeoutPanel.setPreferredSize(new Dimension(257, 301));
    UIUtilities.setFontSize(takeoutLabel, 20f);
    takeoutPanel.add(takeoutImage);
    takeoutPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    takeoutPanel.add(takeoutLabel);
    takeoutPanel.addMouseListener(new MouseAdapter(){
      @Override
      public void mouseReleased(java.awt.event.MouseEvent e) {
        System.out.print("Hello");
        takeoutPanel.setBackground(DARK_GREEN);
      }

      @Override
      public void mousePressed(MouseEvent e) {
        takeoutPanel.setBackground(CLICKED_DARK_GREEN);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        takeoutPanel.setBackground(DARK_GREEN);
      }

    });
    
    gbc.weightx = 0.1/0.2;
    gbc.weighty = 1;
    GridBagUtilities.addObject(dineInPanel, this, checkoutPanelLayout, gbc, 0, 0, 1, 1);
    GridBagUtilities.addObject(takeoutPanel, this, checkoutPanelLayout, gbc, 0, 1, 1, 1);

    
  }
}