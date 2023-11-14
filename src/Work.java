import java.util.Scanner;
public class Work {
    private double money;
    private String name;
    private Scanner scan;
    private Upgrades upgrades;
    private Menu menu;
    private String ftName;
    private int day;
    public Work(Scanner scan, String name) {
        money = 0;
        this.name = name;
        this.scan = scan;
        day = 1;
        upgrades = new Upgrades(money, scan);
        menu = new Menu(scan);
    }

    public void start() {

        System.out.print("Enter your food truck's name: ");
        ftName = scan.nextLine();

        while (day != 8) {
            System.out.println("\nDay " + day + " of " + name + "'s food truck");
            System.out.print("Add an item to the menu: ");
            String item = scan.nextLine();
            System.out.print("Type the cost: ");
            double cost = scan.nextDouble();
            System.out.println();
            menu.addItem(item, cost);
            menu.printMenu();









            day++;
        }
    }
    public void choice() {
        System.out.println("Today, I'm going to");
        System.out.print("[Work]    [Upgrade]");
        String choice = scan.nextLine();

        if (choice.toLowerCase().equals("work")) {
            work();
        }
        if (choice.toLowerCase().equals("upgrade")) {
            upgrade();
        }
    }
    public void work() {
        double orderCost = 0;
        int orders = (int) (Math.random() * 3) + (int) (Math.random() * menu.getMenu().size()) + 1;
        for (int i = orders ; i > 0; i--) {
            int random = (int) (Math.random() * menu.getMenu().size()) + 1;
            orderCost += menu.getPrices().get(random);
        }
        System.out.println("You had " + orders + " orders. You made $" + orderCost + ".");
        money += orderCost;
    }
    public void upgrade() {
        upgrades.purchase();
    }
}
