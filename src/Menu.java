import java.util.ArrayList;
public class Menu {
    private ArrayList<String> menuItems;
    private ArrayList<Double> prices;
    public Menu() {
        menuItems = new ArrayList<>();
        prices = new ArrayList<>();
    }
    public String getMenu() {
        String string = "";
        for (int i = 0; i < menuItems.size(); i++) {
            string += menuItems.get(i) + "\n";
        }
        return string;
    }
    public String getPrices() {
        String string = "";
        for (int i = 0; i < prices.size(); i++) {
            string += prices.get(i) + "\n";
        }
        return string;
    }
    public void addItem(String item, double price) {
        menuItems.add(item);
        prices.add(price);
    }
}
