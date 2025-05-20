import java.sql.*;
import java.util.ArrayList;

public class RestaurantModel {
  private static final String DB_URL = "jdbc:mysql://localhost/SmartRestaurant";
  private static final String USER = "root";
  private static final String PASS = "mysql";

  public void printArray(ArrayList<String> arr){
    for (String detail : arr){
      System.out.print(detail);
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

  public ResultSet getMenuItemRow(String category){
    ResultSet rs = null;
    
    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM MenuItems WHERE item_category = ?");){
        
        // Set all params to SQL statement
        stmt.setString(1, category);

        // Return result
        rs = stmt.executeQuery();
        
        }
        catch (SQLException e){
          System.err.print(e);
        }
      
    return rs;
 
  }


  

  public static void main(String[] args){
    RestaurantModel restoModel = new RestaurantModel();

    ArrayList<String> row1 = restoModel.getMenuItemRow(1, "MainDish");
  }
}
