package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Utilities.UIUtilities;

public class OrderConfirm extends JDialog {

  public void displayDialog(Component c){

    pack();
    setLocationRelativeTo(c);
    setVisible(true);

  }
  
  private JPanel orderTypePanel;
  private JPanel paymentTypePanel;

  private ButtonGroup orderTypeGroup;
  private ButtonGroup paymentTypeGroup;

  private JRadioButton dineInButton;
  private JRadioButton takeoutButton;
  private JRadioButton cashTypeButton;
  private JRadioButton cardTypeButton;

  private JButton confirmPayButton;

  private Dimension RADIOBUTTON_SIZE = new Dimension(107, 28);

  public void resetState(){
    orderTypeGroup.clearSelection();
    paymentTypeGroup.clearSelection();
  }

  OrderConfirm(Controller mvc){
    setUndecorated(true);
    
    //* Create on construct
    
    orderTypePanel = new JPanel(new FlowLayout());
    paymentTypePanel = new JPanel(new FlowLayout());
    orderTypeGroup = new ButtonGroup();
    paymentTypeGroup = new ButtonGroup();
    dineInButton = new JRadioButton();
    takeoutButton = new JRadioButton();
    cashTypeButton = new JRadioButton();
    cardTypeButton = new JRadioButton();
    confirmPayButton = new JButton();

    Container orderConfirmPane = getContentPane();
    orderConfirmPane.setLayout(new BoxLayout(orderConfirmPane, BoxLayout.PAGE_AXIS));
    

    //? Typecast Container into JComponent to paint border
    ((JComponent) orderConfirmPane).setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.GRAY, 2), new EmptyBorder(5, 5, 5, 5)));

    //* Confirm Pay Button
    confirmPayButton.setAlignmentX(CENTER_ALIGNMENT);
    confirmPayButton.setForeground(UIUtilities.CREAM);
    confirmPayButton.setBackground(UIUtilities.DARK_GREEN);
    confirmPayButton.setText("CONFIRM PAY");
    confirmPayButton.addActionListener(e -> {
      // Timer
      String orderTypeSelection;
      String paymentTypeSelection;
      
      if (orderTypeGroup.getSelection() == null || orderTypeGroup.getSelection() == null){
        dispose();
        return;
      } else {
        orderTypeSelection = orderTypeGroup.getSelection().getActionCommand();
        paymentTypeSelection = paymentTypeGroup.getSelection().getActionCommand();
      }

      mvc.processOrder(paymentTypeSelection, orderTypeSelection);
      mvc.switchPanel("StartP");
      mvc.resetTopBarBehaviorCart();
      mvc.displayBars(false);
      dispose();
      
    });

    //* Dine In Button
    dineInButton.setText("DINE IN");
    dineInButton.setFocusable(false);
    dineInButton.setBackground(null);
    dineInButton.setActionCommand("DINE_IN");
    dineInButton.setPreferredSize(RADIOBUTTON_SIZE);
    
    //* Takeout Button
    takeoutButton.setText("TAKEOUT");
    takeoutButton.setFocusable(false);
    takeoutButton.setBackground(null);
    takeoutButton.setActionCommand("TAKEOUT");
    takeoutButton.setPreferredSize(RADIOBUTTON_SIZE);
    
    //* Cash Type Button
    cashTypeButton.setText("CASH");
    cashTypeButton.setFocusable(false);
    cashTypeButton.setBackground(null);
    cashTypeButton.setActionCommand("CASH");
    cashTypeButton.setPreferredSize(RADIOBUTTON_SIZE);
    
    //* Card Type Button
    cardTypeButton.setText("CARD");
    cardTypeButton.setFocusable(false);
    cardTypeButton.setBackground(null);
    cardTypeButton.setActionCommand("CARD");
    cardTypeButton.setPreferredSize(RADIOBUTTON_SIZE);

    //* Order Type Group
    orderTypeGroup.add(dineInButton);
    orderTypeGroup.add(takeoutButton);

    //* Payment Type Group
    paymentTypeGroup.add(cashTypeButton);
    paymentTypeGroup.add(cardTypeButton);

    //* Order Type Panel Settings
    orderTypePanel.setAlignmentX(CENTER_ALIGNMENT);
    orderTypePanel.add(dineInButton);
    orderTypePanel.add(takeoutButton);

    //* Payment Type Panel Settings
    paymentTypePanel.setAlignmentX(CENTER_ALIGNMENT);
    paymentTypePanel.add(cashTypeButton);
    paymentTypePanel.add(cardTypeButton);


    orderConfirmPane.add(orderTypePanel);
    orderConfirmPane.add(Box.createRigidArea(new Dimension(0, 3)));
    orderConfirmPane.add(paymentTypePanel);
    orderConfirmPane.add(Box.createRigidArea(new Dimension(0, 3)));
    orderConfirmPane.add(confirmPayButton);

  }

}
