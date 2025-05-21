public class OrdersData {
  public final MenuItemData menuItem;
  public final int id;
  public int quantity = 0;
  
  OrdersData (MenuItemData menuItem, int id, int quantity){
    this.menuItem = menuItem;
    this.id = id;
    this.quantity = quantity;
  };


}