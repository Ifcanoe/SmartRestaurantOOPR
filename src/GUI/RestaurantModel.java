package GUI;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


public class RestaurantModel {
  private static final String DB_URL = "jdbc:mysql://localhost/SmartRestaurant";
  private static final String USER = "root";
  private static final String PASS = "mysql";

  private ArrayList<MenuItemData> addedToCart;
  private int currentOrderId = -1;
  private float currentTotal = 0f;


  // * Order Processing
  public void createOrder(String paymentType, String orderType){

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Orders (payment_type, order_date, order_type, order_total) VALUES (?, CURRENT_DATE(), ?, ?)", Statement.RETURN_GENERATED_KEYS)){

        stmt.setString(1, paymentType);
        stmt.setString(2, orderType);
        stmt.setBigDecimal(3, new BigDecimal(Float.toString(currentTotal)));
        stmt.executeUpdate();
        
        try (ResultSet rs = stmt.getGeneratedKeys()){
          if (rs.next()){
            currentOrderId = rs.getInt(1);
            //!!! Debug !!!
            // System.out.println("New Order ID: " + currentOrderId);
          }
        }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void processCartItems(){
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO OrderDetails (order_id, menuitem_id, quantity, item_total) VALUES (?, ?, ?, ?)")){

        conn.setAutoCommit(false);
        
        for (MenuItemData item : addedToCart){
          stmt.setInt(1, currentOrderId);
          stmt.setInt(2, item.id);
          stmt.setInt(3, item.quantity);
          stmt.setBigDecimal(4, new BigDecimal(Float.toString(item.price * item.quantity)));
          stmt.addBatch();
        }

      int[] updates = stmt.executeBatch();

      for (int i = 0; i < updates.length; i++) {
        if (updates[i] == -2){
          System.out.println("Execution " + i + ": unknown number of rows updated");
        } else {
          System.out.println("Execution " + i + "successful: " + updates[i] + " rows updated");
        }
      }

      conn.commit();

      
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // * Query Processes
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

  public ArrayList<MenuItemData> getMenuItemsByCategory(String category, ArrayList<String> allergens){
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


  public void addToCart(MenuItemData itemData){
    //* If item exists in cart, update existing quantity
    for (MenuItemData existing : addedToCart){
      if (existing.id == itemData.id){
        existing.quantity += 1;
        currentTotal += existing.price;
        return;
      }
    }

    currentTotal += itemData.price * itemData.quantity;
    addedToCart.add(itemData);
  }

  public void subtractQuantity(MenuItemData itemData){
    for (MenuItemData existing : addedToCart){
      if (existing.id == itemData.id){
        if (existing.quantity <= 1){
          currentTotal -= existing.price;
          addedToCart.remove(existing);
        } else {
          existing.quantity -= 1;
          currentTotal -= existing.price;
        }
        return;
      }
    }
  }
    
  public void addQuantity(MenuItemData itemData){
    for (MenuItemData existing : addedToCart){
      if (existing.id == itemData.id){
        existing.quantity += 1;
        currentTotal += existing.price;
        return;
      }
    }
  }


  public void createNewOrder(){
    currentTotal = 0;
    addedToCart = new ArrayList<>();
  }

  public ArrayList<MenuItemData> getAddedToCart (){
    return addedToCart;
  }

  public int getCurrentOrderId() {
    return currentOrderId;
  }

  public float getCurrentTotal() {
    return currentTotal;
  }

}
