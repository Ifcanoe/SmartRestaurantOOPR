/*
package RestoGUI.src;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Mai{


	public static JPanel contentPane, OrderPanel, StartPanel, dishOrder;
	
	private static JTextField orderCountF,BudgetF,allegism,srch;

	private static JScrollPane scrollPane;

	public static void main(String[] args) {

		
	
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 800);
		frame.setLayout(null);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 584, 761);
		contentPane.setBackground(new Color(85, 107, 47));
		contentPane.setVisible(true);
		
		OrderPanel = new JPanel();

		OrderPanel.setBounds(0, 0, 584, 761);
		
		OrderPanel.setVisible(false);

		contentPane.add(OrderPanel);

		OrderPanel.setLayout(null);
		
		frame.add(contentPane);
		
		///////////////////

		StartPanel = new JPanel(); 

		StartPanel.setBounds(0, 0, 584, 761);

		contentPane.add(StartPanel);

		StartPanel.setLayout(null);

		

		JButton StartOrderBut = new JButton("Start Order");

		StartOrderBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				StartPanel.setVisible(false);
				OrderPanel.setVisible(true);
			}

		});

		StartOrderBut.setFont(new Font("Verdana", Font.BOLD, 21));

		StartOrderBut.setBounds(191, 350, 201, 78);

		StartPanel.add(StartOrderBut);

		

		JTextPane txtpnsmartDiningRestaurant = new JTextPane();

		txtpnsmartDiningRestaurant.setText("Smart Dining Restaurant \r\n                 2025");

		txtpnsmartDiningRestaurant.setEnabled(false);

		txtpnsmartDiningRestaurant.setEditable(false);

		txtpnsmartDiningRestaurant.setBackground(SystemColor.control);

		txtpnsmartDiningRestaurant.setBounds(227, 705, 129, 45);

		StartPanel.add(txtpnsmartDiningRestaurant);

		

		//////////////

		StartPanel.setVisible(true);

		/////////////


		///////////////////
		JLabel lblNewLabel = new JLabel("Return To Main Menu");

		lblNewLabel.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				StartPanel.setVisible(true);
				OrderPanel.setVisible(false);

			}

		});

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblNewLabel.setBounds(0, 0, 150, 27);

		OrderPanel.add(lblNewLabel);

		

		orderCountF = new JTextField();

		orderCountF.setEditable(false);

		orderCountF.setFont(new Font("Tahoma", Font.PLAIN, 15));

		orderCountF.setText("Order Count:");

		orderCountF.setBounds(52, 38, 186, 33);

		OrderPanel.add(orderCountF);

		orderCountF.setColumns(10);

		

		JLabel lblNewLabel_1 = new JLabel("Budget:");

		lblNewLabel_1.setForeground(new Color(192, 192, 192));

		lblNewLabel_1.setBackground(new Color(85, 107, 47));

		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblNewLabel_1.setBounds(52, 82, 61, 33);

		OrderPanel.add(lblNewLabel_1);


		BudgetF = new JTextField("0.00");

		BudgetF.setForeground(new Color(192, 192, 192));

		BudgetF.setBackground(new Color(85, 107, 47));

		BudgetF.addInputMethodListener(new InputMethodListener() {

			public void caretPositionChanged(InputMethodEvent event) {
				
				
			}

			public void inputMethodTextChanged(InputMethodEvent event) {

				

			}

		});

		BudgetF.setFont(new Font("Tahoma", Font.PLAIN, 15));

		BudgetF.setColumns(10);

		BudgetF.setBounds(107, 82, 131, 33);

		OrderPanel.add(BudgetF);

		

		allegism = new JTextField();

		allegism.setText("Alergens:");

		allegism.setFont(new Font("Tahoma", Font.PLAIN, 15));

		allegism.setColumns(10);

		allegism.setBounds(301, 38, 216, 33);

		OrderPanel.add(allegism);

		

		srch = new JTextField();

		srch.setText("Search:");

		srch.setFont(new Font("Tahoma", Font.PLAIN, 15));

		srch.setColumns(10);

		srch.setBounds(301, 82, 216, 33);

		OrderPanel.add(srch);

		

		

		scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 190, 564, 527);

		OrderPanel.add(scrollPane);

		frame.validate();
		frame.repaint();


		//////////////
		

//		//  to dynamic ng fod sa menu
//
//	        for (int i = 0; i < dishOrder; i++) {
//
//	            JPanel Food[i] = new JFood("Food " + (i + 1));
//
//	          
//
//	            gbc.gridx = i % 3;    remainder columns 
//
//	            gbc.gridy = i / 3;   new row every 3 ng i++ or what ever.
//
//	            
//
//	            panel.add(Food[i], gbc);
//	        }

	}
}*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class SmartRestaurant {
	
	SmartRestaurant(){
    
  // Main Window
	JFrame mainFrame = new JFrame("Smart Restaurant");
    mainFrame.setVisible(true);
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(600, 800);
    mainFrame.setLayout(null);
	
	JPanel StartPanel = new JPanel();
    // StartPanel.setBounds(0, 0, 584, 761);
    StartPanel.setSize(500, 700);
    StartPanel.setLayout(null);
    StartPanel.setVisible(true);
    mainFrame.add(StartPanel);

  JLabel TitleFooter = new JLabel();
    TitleFooter.setText("Smart Dining Restaurant \r\n 2025");
    TitleFooter.setBackground(SystemColor.control);
    TitleFooter.setBounds(220, 705, 150, 45);

    StartPanel.add(TitleFooter);
	
	JPanel OrderPanel = new JPanel();
    OrderPanel.setSize(600, 900);
    OrderPanel.setVisible(false);
    OrderPanel.setLayout(null);
    OrderPanel.setBackground(Color.PINK);
    mainFrame.add(OrderPanel);
	
	JButton StartOrderB = new JButton("Start Order");
    StartOrderB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        StartPanel.setVisible(false);
        OrderPanel.setVisible(true);
      }
    });

    StartOrderB.setFont(new Font("Verdana", Font.BOLD, 21));
    StartOrderB.setBounds(191, 350, 201, 78);

    StartPanel.add(StartOrderB);

  //* Changed to label instead
	
  JButton ReturnMainMenuB = new JButton("Return To Main Menu");
    ReturnMainMenuB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        StartPanel.setVisible(true);
        OrderPanel.setVisible(false);
      }
    });

    ReturnMainMenuB.setHorizontalAlignment(SwingConstants.CENTER);
    ReturnMainMenuB.setFont(new Font("Tahoma", Font.PLAIN, 14));
    ReturnMainMenuB.setSize(150, 27);
    ReturnMainMenuB.setVisible(true);

    OrderPanel.add(ReturnMainMenuB);
	
	JTextField orderCountF = new JTextField();
    orderCountF.setEditable(false);
    orderCountF.setFont(new Font("Tahoma", Font.PLAIN, 15));
    orderCountF.setText("Order Count:");
    orderCountF.setBounds(52, 38, 186, 33);
    orderCountF.setColumns(10);
    orderCountF.setVisible(true);
    orderCountF.setForeground(new Color(192, 192, 192));
    orderCountF.setBackground(new Color(85, 107, 47));
    OrderPanel.add(orderCountF);

  JLabel BudgetLabel = new JLabel();
    BudgetLabel.setText("Budget:");
    BudgetLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
	
	JTextField BudgetF = new JTextField();
    BudgetF.setForeground(new Color(192, 192, 192));
    BudgetF.setBackground(new Color(85, 107, 47));
    BudgetF.setFont(new Font("Tahoma", Font.PLAIN, 15));
    BudgetF.setColumns(10);
    BudgetF.setBounds(52, 82, 186, 33);
    OrderPanel.add(BudgetF);
  
  //! Removed for easier access, put back at some point
	// ((AbstractDocument) BudgetF.getDocument()).setDocumentFilter(new DocumentFilter() {
  //       @Override
  //       public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
  //               throws BadLocationException {
  //           if (offset < 8) return; // block typing before or inside "budget:"
  //           super.insertString(fb, offset, text, attr);
  //       }

  //       @Override
  //       public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
  //               throws BadLocationException {
  //           if (offset < 8) return; // block replacing the prefix
  //           super.replace(fb, offset, length, text, attrs);
  //       }

  //       @Override
  //       public void remove(FilterBypass fb, int offset, int length)
  //               throws BadLocationException {
  //           if (offset < 8) return; // block deleting "budget:"
  //           super.remove(fb, offset, length);
  //       }
  //   });
	
	
	
	JTextField allegism = new JTextField();
	allegism.setText("Alergens: ");
	allegism.setFont(new Font("Tahoma", Font.PLAIN, 15));
	allegism.setColumns(10);
	allegism.setBounds(301, 38, 216, 33);
	allegism.setForeground(new Color(192, 192, 192));
	allegism.setBackground(new Color(85, 107, 47));

  //! Removed for easier access, put back when time is available
	// ((AbstractDocument) allegism.getDocument()).setDocumentFilter(new DocumentFilter() {
  //       @Override
  //       public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
  //               throws BadLocationException {
  //           if (offset < 10) return; // block typing before or inside "budget:"
  //           super.insertString(fb, offset, text, attr);
  //       }

  //       @Override
  //       public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
  //               throws BadLocationException {
  //           if (offset < 10) return; // block replacing the prefix
  //           super.replace(fb, offset, length, text, attrs);
  //       }

  //       @Override
  //       public void remove(FilterBypass fb, int offset, int length)
  //               throws BadLocationException {
  //           if (offset < 10) return; // block deleting "budget:"
  //           super.remove(fb, offset, length);
  //       }
  //   });
	OrderPanel.add(allegism);
	
	JTextField srch = new JTextField();
	srch.setText("Search: ");
	srch.setFont(new Font("Tahoma", Font.PLAIN, 15));
	srch.setForeground(new Color(192, 192, 192));
	srch.setBackground(new Color(85, 107, 47));
	srch.setColumns(10);
	srch.setBounds(301, 82, 216, 33);
  
	((AbstractDocument) srch.getDocument()).setDocumentFilter(new DocumentFilter() {
        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                throws BadLocationException {
            if (offset < 8) return; // block typing before or inside "budget:"
            super.insertString(fb, offset, text, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (offset < 8) return; // block replacing the prefix
            super.replace(fb, offset, length, text, attrs);
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            if (offset < 8) return; // block deleting "budget:"
            super.remove(fb, offset, length);
        }
    });
	OrderPanel.add(srch);
	
	JButton Mdishbut = new JButton("Main Dish");
	Mdishbut.setBounds(10, 135, 120, 33);
	Mdishbut.setBackground(new Color(85, 107, 47));
	Mdishbut.setForeground(Color.white);
	Mdishbut.setVisible(true);
	OrderPanel.add(Mdishbut);
	
	JButton drinksbut = new JButton("Drinks");
	drinksbut.setBounds(155, 135, 120, 33);
	drinksbut.setBackground(new Color(85, 107, 47));
	drinksbut.setForeground(Color.white);
	drinksbut.setVisible(true);
	OrderPanel.add(drinksbut);
	
	JButton Sdishbut = new JButton("Side Dish");
	Sdishbut.setBounds(300, 135, 120, 33);
	Sdishbut.setBackground(new Color(85, 107, 47));
	Sdishbut.setForeground(Color.white);
	Sdishbut.setVisible(true);
	OrderPanel.add(Sdishbut);
	
	JButton dessertbut = new JButton("Side Dish");
	dessertbut.setBounds(444, 135, 120, 33);
	dessertbut.setBackground(new Color(85, 107, 47));
	dessertbut.setForeground(Color.white);
	dessertbut.setVisible(true);
	OrderPanel.add(dessertbut);
	
	JPanel TotalPanel = new JPanel();
	
	TotalPanel.setBackground(Color.BLUE);
	TotalPanel.setBounds(10, 725, 557, 100);
	TotalPanel.setLayout(null);
	OrderPanel.add(TotalPanel);
	
	JLabel totalLab = new JLabel("Total: ");
	totalLab.setBounds(0, 0, 190, 100);
	totalLab.setFont(new Font("Tahoma", Font.BOLD, 24));
	totalLab.setBackground(Color.RED);
	TotalPanel.add(totalLab);
	
	
	
	
	JPanel menuPanel = new JPanel();
	OrderPanel.add(menuPanel);
	menuPanel.setBounds(10, 174, 564, 527);
	GridBagLayout gbl_menuPanel = new GridBagLayout();
	gbl_menuPanel.columnWidths = new int[] {180, 180, 180};
	gbl_menuPanel.rowHeights = new int[] {185, 185, 185, 185, 185, 185, 185};
	gbl_menuPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
	gbl_menuPanel.rowWeights = new double[]{0.0, 0.0};
	menuPanel.setLayout(gbl_menuPanel);
	
	
	/*  dynamic ng fod sa menu
	
	       for (int i = 0; i < numButtons; i++) {
	
	           JPanel Food[i] = new JFood("Food " + (i + 1));
	
	          gbc.gridx = i % 3;    remainder columns
	
	           gbc.gridy = i / 3;   new row every 3 ng i++ or what ever.
	
	           
	
	           panel.add(Food[i], gbc);*/
	
	
	///
	///EXAMPLEPANELS
	///
	Panel panel_1 = new Panel();
	panel_1.setBackground(new Color(128, 0, 0));
	panel_1.setLayout(null);
	GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	gbc_panel_1.fill = GridBagConstraints.BOTH;
	gbc_panel_1.insets = new Insets(0, 0, 5, 5);
	gbc_panel_1.gridx = 0;
	gbc_panel_1.gridy = 1;
	menuPanel.add(panel_1, gbc_panel_1);
	
	Panel panel_2 = new Panel();
	panel_2.setBackground(new Color(128, 0, 0));
	panel_2.setLayout(null);
	GridBagConstraints gbc_panel_2 = new GridBagConstraints();
	gbc_panel_2.fill = GridBagConstraints.BOTH;
	gbc_panel_2.insets = new Insets(0, 0, 5, 5);
	gbc_panel_2.gridx = 1;
	gbc_panel_2.gridy = 1;
	menuPanel.add(panel_2, gbc_panel_2);
	
	Panel panel_3 = new Panel();
	panel_3.setBackground(new Color(128, 0, 0));
	panel_3.setLayout(null);
	GridBagConstraints gbc_panel_3 = new GridBagConstraints();
	gbc_panel_3.fill = GridBagConstraints.BOTH;
	gbc_panel_3.insets = new Insets(0, 0, 5, 5);
	gbc_panel_3.gridx = 2;
	gbc_panel_3.gridy = 1;
	menuPanel.add(panel_3, gbc_panel_3);
	///
	///EXAMPLEPANELS
	///
	JScrollPane scrollPane = new JScrollPane(menuPanel);
	scrollPane.setBounds(10, 174, 557, 550);
	scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	OrderPanel.add(scrollPane);
	
	mainFrame.revalidate();
	mainFrame.repaint();
	}
	public static void main(String[] args) {
		SmartRestaurant pa = new SmartRestaurant();
	}
}

