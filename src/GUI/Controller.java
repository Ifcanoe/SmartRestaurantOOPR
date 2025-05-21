package GUI;
import java.util.ArrayList;

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
  public void displayCategory(String category){{
    MenuPanel menuPanel = view.getMenuPanel();

    // Reset display
    menuPanel.resetDisplay();

    ArrayList<MenuItemData> items = model.getMenuItemsByCategory(category);

    for (MenuItemData item : items) {
      menuPanel.addMenuItem(item);
    }
  }
  
  }

  public void modelCreateNewOrder(){
    CheckoutPanel checkoutPanel = view.getCheckoutPanel();
    checkoutPanel.resetDisplay();
    model.createNewOrder();
  }

  public void printCart(){
    ArrayList<MenuItemData> addedToCart = model.getAddedToCart();

    for (MenuItemData item : addedToCart){
      System.out.print(item.id);
      System.out.print(item.name);
      System.out.print(item.quantity);
      System.out.print(item.price);
    }
  }

  public void addToCart(MenuItemData itemData){
    // Model deduplication and quantity bump
    model.addToCart(itemData);

    CheckoutPanel checkoutPanel = view.getCheckoutPanel();
    checkoutPanel.resetDisplay();

    for (MenuItemData item : model.getAddedToCart()) {
      checkoutPanel.addOrderItem(item);
    }

  }

  public void subtractItem(MenuItemData itemData){
    CheckoutPanel checkoutPanel = view.getCheckoutPanel();
    
    // Model deduplication and quantity 
    model.subtractQuantity(itemData);
    checkoutPanel.resetDisplay();
    
    
    for (MenuItemData item : model.getAddedToCart()) {
      checkoutPanel.addOrderItem(item);
    }
    
  } 

  public void addItem(MenuItemData itemData){
    CheckoutPanel checkoutPanel = view.getCheckoutPanel();
    
    // Model deduplication and quantity 
    model.addQuantity(itemData);
    checkoutPanel.resetDisplay();
    
    
    for (MenuItemData item : model.getAddedToCart()) {
      checkoutPanel.addOrderItem(item);
    }
    
  } 

  public void handleMenuItem(MenuItemData itemData){
    
  }
}
