package Utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.InputStream;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class UIUtilities {

  public static Font DEFAULT_FONT;
  public static Font baseFont;
  public static final Color DARK_GREEN = new Color(6, 82, 4);
  public static final Color RESTO_GRAY = new Color(80, 80, 80);
  public static final Color CREAM = new Color(255, 245, 225);
  public static final Color RESTO_BROWN = new Color(82, 45, 26);
  public static final Color INVIS = new Color(000, true);
  public static final Color ORANGE = new Color(213, 160, 90);

  static {
    try {
      InputStream is = UIUtilities.class.getResourceAsStream("/images/MontserratBold.ttf");
      baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
      DEFAULT_FONT = baseFont.deriveFont(Font.PLAIN, 16f);

      UIDefaults defaults = UIManager.getLookAndFeelDefaults();
      defaults.put("Button.focus", INVIS);

    } catch (Exception e){
      System.err.print(e.getMessage());
    }
  }

  public static void setFontSize(Component c, float size){
    c.setFont(baseFont.deriveFont(Font.PLAIN, size));
  }

  public static void applyDefaultFont() {
    UIManager.put("Label.font", DEFAULT_FONT);
    UIManager.put("Button.font", DEFAULT_FONT);
    UIManager.put("TextField.font", DEFAULT_FONT);
    UIManager.put("TextArea.font", DEFAULT_FONT);
    UIManager.put("ComboBox.font", DEFAULT_FONT);
    UIManager.put("TabbedPane.font", DEFAULT_FONT);
    UIManager.put("CheckBox.font", DEFAULT_FONT);
    UIManager.put("RadioButton.font", DEFAULT_FONT);
  }

  public static void applyDefaultForeground() {
    UIManager.put("Label.foreground", RESTO_GRAY);
    UIManager.put("Button.foreground", RESTO_GRAY);
    UIManager.put("TextField.foreground", RESTO_GRAY);
    UIManager.put("TextArea.foreground", RESTO_GRAY);
    UIManager.put("ComboBox.foreground", RESTO_GRAY);
    UIManager.put("TabbedPane.foreground", RESTO_GRAY);
    UIManager.put("CheckBox.foreground", RESTO_GRAY);
    UIManager.put("RadioButton.foreground", RESTO_GRAY);
  }

  public static void applyDefaultBackground() {
    UIManager.put("Panel.background", CREAM);
  }

  public static void applyDefaultEditable() {
    UIManager.put("Label.editable", false);
  }

}
