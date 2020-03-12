package classes.menus;

import classes.enums.Option;

public class CustomerMenu {
    InputTaker input = new InputTaker();


    public void displayCustomerMenu() throws Exception {
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
            Option option = input.getOptionInt();
            switch (option) {
                case ONE:
                    break;
                case TWO:
                    break;
                case THREE:
                    checkBasket();
                    break;
                case FOUR:;
                    placeOrder();
                    break;
                case FIVE:
                    ordersHistory();
                    break;
                case SIX:
                    viewListOfAvailableProducts();
                    break;
                case SEVEN:
                    viewProductsInCategory();
                    break;
                case EIGHT:
                    checkIfProductAvailable();
                    break;
                case NINE:
                    rateItem();
                case ZERO:
                    isRunning = false;
                default: throw new Exception("Something went wrong.");
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
