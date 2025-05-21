package GUI;
import java.sql.*;
import java.util.ArrayList;

public class RestaurantModel {
  private static final String DB_URL = "jdbc:mysql://localhost/SmartRestaurant";
  private static final String USER = "root";
  private static final String PASS = "mysql";

  private ArrayList<MenuItemData> addedToCart;

  public void printArray(ArrayList<MenuItemData> arr){
    for (MenuItemData thing : arr){
      System.out.println(thing.imagePath);
    }
  }

  public ResultSet getMenuItemRow(int id, String category){
    ResultSet rs = null;
    
    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM MenuItems WHERE menuitem_id = ? AND item_category = ?");){
        
        // Set all params to SQL statement
        stmt.setInt(1, id);
        stmt.setString(2, category);

        // Return result
        rs = stmt.executeQuery();
        
        }
        catch (SQLException e){
          System.err.print(e);
        }
      
    return rs;
 
  }

  public ArrayList<MenuItemData> getMenuItemsByCategory(String category) {
    ArrayList<MenuItemData> items = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM MenuItems WHERE item_category = ?")){

        // Set params to statement 
        stmt.setString(1, category);
        ResultSet rs = stmt.executeQuery();

        // Iterate through and set MenuItemData to OrderData
        while (rs.next()) {
          items.add(new MenuItemData(
            rs.getInt("menuitem_id"),
            rs.getString("item_name"),
            rs.getInt("item_quantity"),
            rs.getFloat("item_price"),
            rs.getString("item_code"),
            rs.getString("item_category"),
            rs.getString("image_link"),
            rs.getInt("calories")
          ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return items;
}
  
  public float updateTotal(){
    float total = 0;

    for (MenuItemData item : addedToCart){
      total += item.price * item.quantity;
    }

    return total;
  }

  public void addToCart(MenuItemData itemData){
    for (MenuItemData existing : addedToCart){
      if (existing.id == itemData.id){
        existing.quantity += 1;
        return;
      };
    }
    addedToCart.add(itemData);
  }

  public void subtractQuantity(MenuItemData itemData){
    if (itemData.quantity <= 1 ){ 
      for (MenuItemData existing : addedToCart){
        if (existing.id == itemData.id){
          addedToCart.remove(existing);
          return;
        }
      }
    } else {
        for (MenuItemData existing : addedToCart){
          if (existing.id == itemData.id){
            existing.quantity -= 1;
            return;
          };
        }
    }
  }
  
  public void addQuantity(MenuItemData itemData){
    for (MenuItemData existing : addedToCart){
      if (existing.id == itemData.id){
        existing.quantity += 1;
        return;
      };
    }
  }

  public void createNewOrder(){
    addedToCart = new ArrayList<>();
  }

  public ArrayList<MenuItemData> getAddedToCart (){
    return addedToCart;
  }
  


}
