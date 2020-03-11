package classes.menus;

public class CustomerMenu {
    InputTaker input = new InputTaker();


    public void displayCustomerMenu() {
        boolean isRunning = true;
        System.out.println("You are logged as customer");
        while(isRunning) {
            System.out.println("""
                    (1) Add product to basket
                    (2) Delete product from basket
                    (3) See all products from basket
                    (4) Place an order
                    (5) See previous orders
                    (6) See all available products
                    (7) See all products from specific category
                    (8) Check availability of specific product
                    (9) Rate product
                    (10) See statistic of your orders
                    (0) Quit""");
            int userInput = input.getIntInput();
            switch (userInput) {
                case 1:
                    addItemToBasket();
                    break;
                case 2:
                    deleteItemFromBasket();
                    break;
                case 3:
                    checkBasket();
                    break;
                case 4:
                    placeOrder();
                    break;
                case 5:
                    ordersHistory();
                    break;
                case 6:
                    viewListOfAvailableProducts();
                    break;
                case 7:
                    viewProductsInCategory();
                    break;
                case 8:
                    checkIfProductAvailable();
                    break;
                case 9:
                    rateItem();
                case 10:
                    getOrdersStatistics();
                case 0:
                    isRunning = false;
            }
        }
    }

    private void addItemToBasket() {

    }

    private void deleteItemFromBasket() {
    }

    private void checkBasket() {

    }

    private void placeOrder() {

    }

    private void ordersHistory() {

    }

    private void viewListOfAvailableProducts() {

    }

    private void viewProductsInCategory() {

    }

    private void checkIfProductAvailable() {

    }

    private void rateItem() {

    }

    private void getOrdersStatistics() {

    }
}
