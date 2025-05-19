import java.sql.*;

public class RestaurantModel {
  private static final String DB_URL = "jdbc:mysql://localhost/SmartRestaurant";
  private static final String USER = "root";
  private static final String PASS = "mysql";

  public void getRowDetails(String table_name, String id_col, int id){
    
    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.prepareStatement("SELECT * FROM ? WHERE ? = ?");){ 
          ResultSet rs;


        }
        catch (SQLException e){
        e.printStackTrace();
      }
    
  }

  public static void main(String[] args){
    try{Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM MenuItems");

        while (rs.next()){
          


        }          


        }
        catch (SQLException e){
        e.printStackTrace();
      }
  }
    
}
