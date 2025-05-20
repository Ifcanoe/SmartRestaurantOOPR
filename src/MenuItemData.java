public class MenuItemData {
  public final int id;
  public final String name;
  public final int quantity;
  public final float price;
  public final String code;
  public final String category;
  public final String imagePath;
  public final int calories;


  public MenuItemData(int id, String name, int quantity, float price, String code, String category, String imagePath, int calories) {
    this.id = id;
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.code = code;
    this.category = category;
    this.imagePath = imagePath;
    this.calories = calories;
  }
}
