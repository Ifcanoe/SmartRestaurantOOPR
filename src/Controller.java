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
  public void mainDishesDisplay(){
    

  }
}