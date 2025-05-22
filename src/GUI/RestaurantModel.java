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
  private float currentBudget = 0f;


  // * Order Processing
  public void createOrder(String paymentType, String orderType){

    if (addedToCart.isEmpty()) { 
      return; 
    }

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

        if (addedToCart.isEmpty()) { 
          return; 
        }


        
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
    String SQL;
    System.out.print(currentBudget);

    if (currentBudget <= 0){
      SQL = "SELECT * FROM MenuItems WHERE item_category = ?";
    } else {
      SQL = "SELECT * FROM MenuItems WHERE item_category = ? AND item_price < ?";
    }

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(SQL)){

        // Set params to statement 
        if (currentBudget <= 0){
          stmt.setString(1, category);
        } else {
          stmt.setString(1, category);
          stmt.setBigDecimal(2, new BigDecimal(Float.toString(currentBudget)));
        }
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
    String SQL;

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < allergens.size(); i++) {
      sb.append("?");
      if (i < allergens.size() - 1) {
        sb.append(",");
      }
    }

    String placeholders = sb.toString();

    if (currentBudget <= 0){
    SQL = "SELECT DISTINCT mi.* FROM MenuItems mi " +
                "WHERE mi.menuitem_id NOT IN ( " +
                "  SELECT ma.menuitem_id FROM MenuItemAllergens ma " +
                "  JOIN Allergens a ON ma.allergen_id = a.allergen_id " +
                "  WHERE LOWER(a.allergen_name) IN (" + placeholders + ") " +
                ") AND mi.item_category = ?";  
    } else {
      SQL = "SELECT DISTINCT mi.* FROM MenuItems mi " +
                "WHERE mi.menuitem_id NOT IN ( " +
                "  SELECT ma.menuitem_id FROM MenuItemAllergens ma " +
                "  JOIN Allergens a ON ma.allergen_id = a.allergen_id " +
                "  WHERE LOWER(a.allergen_name) IN (" + placeholders + ") " +
                ") AND mi.item_category = ? AND mi.item_price < ?";
    }
    

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(SQL)){

        // Set params to statement
        if (currentBudget <= 0){
          for (int i = 0; i < allergens.size(); i++){
            stmt.setString(i + 1, allergens.get(i).toLowerCase());
          }
          stmt.setString(allergens.size() + 1, category);
        } else {  
          for (int i = 0; i < allergens.size(); i++){
            stmt.setString(i + 1, allergens.get(i).toLowerCase());
          }
          stmt.setString(allergens.size() + 1, category);
          stmt.setBigDecimal(allergens.size() + 2, new BigDecimal(Float.toString(currentBudget)));
        }
        

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

  public ArrayList<String> getAllergens(){
    ArrayList<String> allergens = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement()){

        ResultSet rs = stmt.executeQuery("SELECT allergen_name FROM allergens");

        // Iterate through query and set data to allergens
        while (rs.next()) {
          allergens.add(rs.getString("allergen_name"));
        }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return allergens;
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

  public void setCurrentBudget(float budget){
    this.currentBudget = budget;
  }

}
