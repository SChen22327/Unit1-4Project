import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Menu {
    private Scanner scan;
    private ArrayList<String> menuItems;
    private ArrayList<Double> prices;
    public Menu(Scanner scan) {
        menuItems = new ArrayList<>();
        prices = new ArrayList<>();
        this.scan = scan;
    }
    public void printMenu() {
        String string = "     Menu\n";
        for (int i = 0; i < menuItems.size(); i++) {
            string += menuItems.get(i) + "  |  $" + String.format("%.2f",prices.get(i)) + "\n";
        }
        System.out.println(string);
    }
    public ArrayList getMenu() {
        return menuItems;
    }
    public ArrayList<Double> getPrices() {
        return prices;
    }

    public void addItem() {
        String item = askItem();
        double price = askCost();
        while (price >= 11) {
            System.out.println("That's too expensive! No one will buy that!\nTry a different price.");
            price = askCost();
        }
        menuItems.add(item);
        prices.add(price);
    }
    public void addItem(String item) {
        menuItems.add(item);
        double price = 0;
        prices.add(price);
    }
    private String askItem() {
        System.out.print("Add an item to the menu: ");
        return scan.nextLine();
    }
    private double askCost() {
        System.out.print("Type the cost: ");
        double cost = scan.nextDouble();
        scan.nextLine();
        return cost;
    }
}
