package Main;

import Utilities.UIUtilities;

// import Utilities.ImageUtilities;

// import javax.swing.JFrame;
// import javax.swing.JLabel;

import GUI.MainFrameView;


//? Test
// class Test extends JFrame{
  
//   Test(){

//     JLabel txt = new JLabel();
//     txt.setIcon(ImageUtilities.createHamburger());

//     add(txt);
//     pack();
//     setVisible(true);
//   }
// }

public class Main {
  
  public static void main(String[] args){
    UIUtilities.applyDefaultBackground();
    UIUtilities.applyDefaultForeground();
    UIUtilities.applyDefaultFont();
    UIUtilities.applyDefaultEditable();    
    // new Test();

    @SuppressWarnings(value = { "myApp", "unused" })
    MainFrameView myApp = new MainFrameView();
  }

}


