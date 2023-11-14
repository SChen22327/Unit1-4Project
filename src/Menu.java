import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Menu {
    private ArrayList<String> menuItems;
    private ArrayList<Double> prices;
    public Menu(Scanner scan) {
        menuItems = new ArrayList<>();
        prices = new ArrayList<>();
    }
    public void printMenu() {
        String string = "";
        for (int i = 0; i < menuItems.size(); i++) {
            string += menuItems.get(i) + "  |  $" + prices.get(i) + "\n";
        }
        System.out.println(string);
    }
    public ArrayList getMenu() {
        return menuItems;
    }
    public ArrayList<Double> getPrices() {
        return prices;
    }

    public void addItem(String item, double price) {
        menuItems.add(item);
        prices.add(price);
    }
}
