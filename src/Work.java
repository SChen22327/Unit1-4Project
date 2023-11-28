/*  (1) https://www.javaprogramto.com/2020/08/how-to-make-delay-in-java-thread-sleep.html
    (2)
 */
import java.util.Scanner;
import java.util.ArrayList;
public class Work {
    private double money;
    private String name;
    private Scanner scan;
    private Upgrades upgrades;
    private Menu menu;
    private String ftName;
    private int day;
    private int actions;
    private boolean easyMode;
    public Work(Scanner scan, String name) {
        money = 0;
        this.name = name;
        this.scan = scan;
        day = 1;
        System.out.print("Do you want to play in easy mode? All upgrades except actions will start at level 2.\nEnter 1 for easy mode, 2 for normal mode: ");
        String n = scan.nextLine();
        if (n.toLowerCase().equals("1")) {
            upgrades = new Upgrades(scan, true);
        } else {
            upgrades = new Upgrades(scan);
        }
        menu = new Menu(scan);
        actions = 1;
        easyMode = true;
    }

    public void start() {
        System.out.print("Enter your food truck's name: ");
        ftName = scan.nextLine();
        System.out.println("""
                The rules of this game are
                  1. You have 7 days/rounds to earn $300.
                  2. You can buy upgrades which will greatly increase the money you earn but decreases the amount of money you have(see rule 5).
                  3. You can work each day to earn money. Upgrades will be applied at the end of each day.
                  4. You can ONLY choose to work or upgrade each day, you can't do both.
                      a. The amount of money made somewhat depends on the number of items on your menu, which increases by 1 each day, meaning you'll likely earn more later on.
                      b. If you choose to upgrade, you may only choose to upgrade one thing.
                      c. The condition is changed if you upgrade number of actions, max 2 actions per day.
                  5. If you fail to earn enough money or run out of money, you will lose.""");

        while (day != 8 && money >= 0) {          //THIS IS THE WORK DAY LOOP//
            System.out.println("\nDay " + day + " of " + name + "'s food truck");
            System.out.println("Current Balance: $" + String.format("%.2f", money));
            if (upgrades.incrementActions(actions)) {
                actions++;
            }
            System.out.println("# of Actions: " + actions);
            System.out.println();
            System.out.print("Enter \"yes\" if you want to make an item free, enter no to add a price. Be warned that this may affect your earnings: ");
            String y = scan.nextLine();
            if (y.toLowerCase().equals("yes")) {
                System.out.println("You chose to make this item free.");
                System.out.print("Enter item to make free(I suggest water): ");
                String item = scan.nextLine();
                menu.addItem(item);
            } else {
                System.out.println("You chose to give it a price.");
                menu.addItem();
            }
            System.out.println();
            menu.printMenu();
            for (int i = 0; i < actions; i++) {
                choice();
                if (actions == 2) {
                    System.out.println("Current Balance: $" + String.format("%.2f", money));
                }
            }
            day++;
        }
        if (money < 0) {
            System.out.println("You ran out of money and your food truck has gone out of business!");
        } else if (money >= 200) {
            System.out.println("Congratulations! Your food truck was a success and you hit your goal of $300!");
        } else {
            System.out.println("You couldn't make your budget in time and now you're poor and homeless.\n");
            sleep(3000);
            System.out.println("FOREVER");
        }
    }
    private void choice() {
        System.out.println("Today, I'm going to(type name)");
        System.out.println("     [Work]    [Upgrade]");
        String choice = scan.nextLine();

        if (choice.toLowerCase().equals("work")) {
            work();
        } else if (choice.toLowerCase().equals("upgrade")) {
            upgrade();
        } else {
            System.out.println("That's not an option!");
            choice();
        }
    }
    private void work() {  //CALCULATES MONEY EARNED AND UPGRADE BONUSES//
        double orderCost = 0;
        int orders = (int) (Math.random() * 3) + (int) (Math.random() * menu.getMenu().size()) + 1;
        for (int i = orders ; i > 0; i--) {
            int random = (int) (Math.random() * menu.getMenu().size());
            orderCost += menu.getPrices().get(random);
        }
        orderCost = Math.round(orderCost * upgrades.calculateBonus() * 100) / 100.0;
        System.out.println("You had " + orders + " orders. With current level of upgrades, you made $" + String.format("%.2f",orderCost) + ".");
        money += orderCost;
    }
    public void upgrade() {
        double temp = upgrades.purchase(money);
        if (temp == -1) {
            choice();
        } else {
            money = temp;
        }
    }
    private void sleep(long time) { // (1) just here so I can make losing have a dramatic effect
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Thread is interrupted");
            Thread.currentThread().interrupt();
        }
    }
}