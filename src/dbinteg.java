// Java program to implement a simple JDBC application
import java.sql.*;
import java.math.*;

public class dbinteg {
  public static void main(String[] args)
  {
    // Database URL, username, and password
    String url = "jdbc:mysql://localhost:3306/smartrestaurant";
    String username = "root";
    String password = "mysql";

    String exampleQuery = "SELECT * from menuitems";
    String exampleQuery2 = "SELECT * FROM MenuItemIngredientsNames";

    try {

      Class.forName("com.mysql.cj.jdbc.Driver");

      // Establish connection
      Connection conn = DriverManager.getConnection(url, username, password);
      Statement st = conn.createStatement();

      // Execute the query
      ResultSet rs = st.executeQuery(exampleQuery2);
      int colsCount = rs.getMetaData().getColumnCount();

      while (rs.next()){
        // String item_name = rs.getString("item_name");
        // String item_code = rs.getString("item_code");
        // BigDecimal price = rs.getBigDecimal("item_price");
        // String item_category = rs.getString("item_category");
        // System.out.printf("%s %s %s %s\n", item_name, item_code, price, item_category);
        String item_name, ingredient_name;
        item_name = rs.getString("item_name");
        ingredient_name = rs.getString("ingredient_name");
        System.out.printf("%s %s\n", item_name, ingredient_name);
      }




      // Close the connection
      st.close();
      conn.close();
      System.out.println("Connection closed.");
    }

    catch (ClassNotFoundException e) {
      System.err.println("JDBC Driver not found: " + e.getMessage());
    }

    catch (SQLException e) {
      System.err.println("SQL Error: " + e.getMessage());
    }
  }
}