package GUI.CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JButton;

import Utilities.UIUtilities;

public class RoundedButtonS extends JButton {
  private int cornerRadius = 50;
  private Color textColor = UIUtilities.CREAM;

  public RoundedButtonS(String text) {
    super(text);
    setOpaque(false);             
    setFocusPainted(false);       
    setForeground(textColor);
    setContentAreaFilled(false);  
    setBorderPainted(false);   
  }

  @Override
    protected void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g.create();

      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Create shadow beneath
      g2.setColor(new Color(0, 0, 0, 60));
      g2.fillRoundRect(0, 5, getWidth() - 4, getHeight() - 4, cornerRadius, cornerRadius);

      // Create the round rectangle
      g2.setColor(getBackground());
      g2.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, cornerRadius, cornerRadius);

      g2.dispose();
      super.paintComponent(g); 
  }

  @Override
  public void setBackground(Color bg) {
    super.setBackground(bg);
  }

  @Override
  public Insets getInsets() {
    return new Insets(0, 0, 5, 0); // Internal padding for text/icon
  }

  @Override
  public Dimension getPreferredSize() {
    Dimension size = super.getPreferredSize();
    Insets insets = getInsets();
    return new Dimension(
      size.width - insets.left - insets.right,
      size.height - insets.top - insets.bottom
    );
  }

  public void setCornerRadius(int radius) {
    this.cornerRadius = radius;
    repaint();
  }

  public void setTextColor(Color color) {
    this.textColor = color;
    setForeground(color);
    repaint();
  }

}