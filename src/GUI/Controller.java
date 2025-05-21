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

  //* Confirm Dialog Controller
  public void processOrder(String paymentType, String orderType){
    model.createOrder(paymentType, orderType);

    model.processCartItems();

  }

  //* Checkout Panel Controller
  public void prepareTopCheckout(){
    TopBar topBar = view.getTopBar();

    topBar.configureReturnButton("BACK", e -> {
      view.displayPanel("CartP");
      prepareTopCart();
    });

  }

  //* Cart Panel Controller
  public void prepareTopCart(){
    TopBar topBar = view.getTopBar();

    topBar.configureCartButton(false, e -> {});
    topBar.configureReturnButton("BACK", e -> {
      view.displayPanel("MenuP");
      resetTopBarBehaviorCart();
    });

  }

  public void resetTopBarBehaviorCart(){
    TopBar topBar = view.getTopBar();

    topBar.configureCartButton(true, e -> {
      switchPanel("CartP");
      prepareTopCart();
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

  // * Cart Panel Controller
  public void modelCreateNewOrder(){
    CartPanel CartPanel = view.getCartPanel();
    BottomBar bottomBar = view.getBottomBar();

    CartPanel.resetDisplay();
    model.createNewOrder();
    bottomBar.setTotalTextField(model.getCurrentTotal());
  }

  public void printCart(){
    ArrayList<MenuItemData> addedToCart = model.getAddedToCart();
    int i = model.getCurrentOrderId();
    float f = model.getCurrentTotal();

    for (MenuItemData item : addedToCart){
      System.out.println(item.id);
      System.out.println(item.name);
      System.out.println(item.quantity);
      System.out.println(item.price);
    }
    
    System.out.printf("%d, %.1f", i, f);

  }

  public void emptyCart(){
    CartPanel cartPanel = view.getCartPanel();
    BottomBar bottomBar = view.getBottomBar();

    model.createNewOrder();
    bottomBar.setTotalTextField(model.getCurrentTotal());
    cartPanel.resetDisplay();

  }
  
  public void addToCart(MenuItemData itemData){
    // Model deduplication and quantity bump
    model.addToCart(itemData);
    BottomBar bottomBar = view.getBottomBar();

    CartPanel cartPanel = view.getCartPanel();
    bottomBar.setTotalTextField(model.getCurrentTotal());
    
    cartPanel.resetDisplay();

    for (MenuItemData item : model.getAddedToCart()) {
      cartPanel.addOrderItem(item);
    }

  }

  public void subtractItem(MenuItemData itemData){
    CartPanel cartPanel = view.getCartPanel();
    BottomBar bottomBar = view.getBottomBar();
    
    // Model deduplication and quantity 
    model.subtractQuantity(itemData);
    cartPanel.resetDisplay();
    bottomBar.setTotalTextField(model.getCurrentTotal());
    
    for (MenuItemData item : model.getAddedToCart()) {
      cartPanel.addOrderItem(item);
    }

    
  } 

  public void addItem(MenuItemData itemData){
    CartPanel CartPanel = view.getCartPanel();
    BottomBar bottomBar = view.getBottomBar();
    
    // Model deduplication and quantity 
    model.addQuantity(itemData);
    CartPanel.resetDisplay();
    bottomBar.setTotalTextField(model.getCurrentTotal());
    
    
    for (MenuItemData item : model.getAddedToCart()) {
      CartPanel.addOrderItem(item);
    }
    
  } 

}
