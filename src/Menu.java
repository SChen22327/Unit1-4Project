import java.util.ArrayList;
import java.util.Scanner;
/**This class represents a Menu object.
 * @author Stanford*/
public class Menu {
    /**This is the scanner for getting user input.*/
    private Scanner scan;
    /**This is an ArrayList for the items on the menu.*/
    private ArrayList<String> menuItems;
    /**This is an ArrayList for the prices on the menu.*/
    private ArrayList<Double> prices;
    /**Instantiates a Menu object
     * @param scan scanner variable*/
    public Menu(Scanner scan) {
        menuItems = new ArrayList<>();
        prices = new ArrayList<>();
        this.scan = scan;
    }
    /**Asks user for a menu item and price. Then adds it to the corresponding ArrayList.*/
    public void addItem() {
        System.out.println("The max price is $10.99.");
        String item = askItem();
        if (item.length() > 9) {
            item = item.substring(0, 9) + "...";
        } else {
            for(int i = item.length(); i < 12; i++) {
                item += " ";
            }
        }
        double price = askCost();
        while (price >= 11 || price <0) {
            while (price >= 11) {
                System.out.println("That's too expensive! No one will buy that!\nTry a different price.");
                price = askCost();
            }
            while (price < 0) {
                System.out.println("That's a negative number! Don't be insane, try a different price.");
                price = askCost();
            }
        }
        menuItems.add(item);
        prices.add(price);
    }
    /**Returns the Menu ArrayList
     * @return menuItems*/
    public ArrayList getMenu() {
        return menuItems;
    }
    /**Returns the Prices ArrayList
     * @return prices*/
    public ArrayList<Double> getPrices() {
        return prices;
    }
    /**Creates a string with the menu item and its price. It then prints the string as a menu.*/
    public void printMenu() {
        String string = "          Menu\n";
        for (int i = 0; i < menuItems.size(); i++) {
            string += menuItems.get(i) + "  |  $" + String.format("%.2f",prices.get(i)) + "\n";
        }
        System.out.println(string);
    }
    /**Overloaded method that adds a provided item to the menu and makes the item free(adds 0 to the Prices ArrayList)
     *@param item item name*/
    public void addItem(String item) {
        if (item.length() > 9) {
            item = item.substring(0, 9) + "...";
        } else {
            for (int i = item.length(); i < 12; i++) {
                item += " ";
            }
        }
        menuItems.add(item);
        double price = 0;
        prices.add(price);
    }
    /**Asks user for an item and return it as a String
     * @return item name*/
    private String askItem() {
        System.out.print("Add an item to the menu: ");
        return scan.nextLine();
    }
    /**Asks user for a price and return it as a double
     * @return item price*/
    private double askCost() {
        System.out.print("Type the cost: ");
        double cost = scan.nextDouble();
        scan.nextLine();
        return cost;
    }
}
