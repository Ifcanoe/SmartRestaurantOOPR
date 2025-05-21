package Utilities;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageUtilities {
  
  //? Test
  // public static ImageIcon createHamburger (){
  //   URL imageUrl = ImageUtilities.class.getResource("/Assets/MainDishes/newmenu/hamburger.png");
  //   if (imageUrl == null) {
  //     System.err.println("Image not found!");
  //   } else {
  //     return new ImageIcon(imageUrl);
  //   }

  //   return null;
  // }

  public static ImageIcon resizeImage(ImageIcon img, int w, int h){
    Image scaledImage = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);

    return new ImageIcon(scaledImage);
  }

  public static ImageIcon getImage(String path){
    ImageIcon image = new ImageIcon(ImageUtilities.class.getResource(path));
    return image;
  }

  public static ImageIcon getImage(String path, int w, int h){
    ImageIcon image = new ImageIcon(ImageUtilities.class.getResource(path));

    image = resizeImage(image, w, h);
    return image;
  }

}