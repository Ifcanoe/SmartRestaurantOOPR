package GUI;
import java.util.ArrayList;

public class Controller {
  private MainFrameView view;
  private RestaurantModel model;

  private ArrayList<String> currentlySelectedAllergens = new ArrayList<>();

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

  //* Allergens Dialog Controller */
  public void displayAllergens(){
    MenuPanel menuPanel = view.getMenuPanel();
    AllergensDialog allergensDialog = menuPanel.getAllergensDialog();

    ArrayList<String> allergens = model.getAllergens(); // Fetch all allergens from the model
    allergensDialog.addAllergenItem(allergens);
  }

  public void resetCurrentlySelectedAllergens(){
    currentlySelectedAllergens.clear();
  }

  public void displayCategory(String category){
    MenuPanel menuPanel = view.getMenuPanel();
    ArrayList<String> selectedAllergens = menuPanel.getAllergensDialog().getSelectedAllergens();

    // Reset the display before adding new items
    menuPanel.resetDisplay();

    ArrayList<MenuItemData> items;
    if (selectedAllergens.isEmpty()){
      //* If no allergens are selected, display all items for the category
      items = model.getMenuItemsByCategory(category);
    } else {
      //* If allergens are selected, filter items based on those allergens
      items = model.getMenuItemsByCategory(category, selectedAllergens);
    }

    for (MenuItemData item : items) {
      menuPanel.addMenuItem(item);
    }
  }
  

  //* Confirm Dialog Controller
  public void processOrder(String paymentType, String orderType){
    System.out.println(paymentType);
    System.out.println(orderType);


    if (paymentType == null || paymentType.isEmpty()){
      return;
    }
    if (orderType == null || paymentType.isEmpty()){
      return;
    }
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

  // * Cart Panel Controller
  public void modelCreateNewOrder(){
    CartPanel cartPanel = view.getCartPanel();
    BottomBar bottomBar = view.getBottomBar();

    cartPanel.resetDisplay();
    model.createNewOrder();
    bottomBar.setTotalTextField(model.getCurrentTotal());

    cartPanel.getOrderConfirm().resetState();
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
