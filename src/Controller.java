import java.util.ArrayList;
import java.sql.ResultSet;


public class Controller {
  private MainFrameView view;
  private RestaurantModel model;

  public Controller(MainFrameView view, RestaurantModel model){
    this.view = view;
    this.model = model;
  }

  public void switchPanel(String panelName){
    view.displayPanel(panelName);
  }

  public void displayBars(boolean state){
    view.displayComponent(view.getTopBar(), state);
    view.displayComponent(view.getBottomBar(), state);
  }

  public void prepareTopCheckout(){
    TopBar topBar = view.getTopBar();

    topBar.configureCartButton(false, e -> {});
    topBar.configureReturnButton("BACK", e -> {
      view.displayPanel("MenuP");
      resetTopBarBehavior();
    });
  
  }

  public void resetTopBarBehavior(){
    TopBar topBar = view.getTopBar();

    topBar.configureCartButton(true, e -> {
      switchPanel("CheckoutP");
      prepareTopCheckout();
    });

    topBar.configureReturnButton("BACK TO MAIN MENU", e -> {
      switchPanel("StartP");
      displayBars(false);
    });
  }

  // * Menu Panel Controller 
  public void displayMainDishes(){
    // Grab all Main Dishes from model
    // create a MenuItemContainer from each RowSet
    int count = 0;
    MenuPanel menuPanel = view.getMenuPanel();
    ResultSet rs = model.getMenuItemRow("MainDish");

    //@params String name, float price, int calories, String path
    while (rs.next()){
      menuPanel.createMenuItem(
        rs.getString(count);
        rs.getFloat();
        rs.getInt();
        rs.getString();
      );
    }

  

    

  }
}