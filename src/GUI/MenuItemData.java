package GUI;
public class MenuItemData {
  public final int id;
  public final String name;
  public int quantity = 0;
  public final float price;
  public final String code;
  public final String category;
  public final String imagePath;
  public final int calories;

  public int getId(){ return id; }

  public int getQuantity(){ return quantity; }

  public void setQuantity(int quantity){ this.quantity = quantity; }

  public MenuItemData(int id, String name, int quantity, float price, String code, String category, String imagePath, int calories) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.id = id;
    this.code = code;
    this.category = category;
    this.imagePath = imagePath;
    this.calories = calories;
  }
}
