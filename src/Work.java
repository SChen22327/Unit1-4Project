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
    public Work(Scanner scan, String name) {
        money = 0;
        this.name = name;
        this.scan = scan;
        day = 1;
        upgrades = new Upgrades(scan);
        menu = new Menu(scan);
        actions = 1;
    }

    public void start() {
        System.out.print("Enter your food truck's name: ");
        ftName = scan.nextLine();
        System.out.println("""
                The rules of this game are
                  1. You have 7 days/rounds to earn $150.
                  2. You can buy upgrades which will greatly increase the money you earn but decreases the amount of money you have(see rule 5).
                  3. You can work each day to earn money. Upgrades will be applied at the end of each day.
                  4. You can ONLY choose to work or upgrade each day, you can't do both.
                      a. The amount of money made somewhat depends on the number of items on your menu, which increases by 1 each day, meaning you'll likely earn more later on.
                      b. If you choose to upgrade, you may only choose to upgrade one thing.
                      c. The condition is changed if you upgrade number of actions, max 2 actions per day.
                  5. If you fail to earn enough money or run out of money, you will lose.""");

        while (day != 8 && money >= 0) {
            System.out.println("\nDay " + day + " of " + name + "'s food truck");
            System.out.println("Current Balance: $" + String.format("%.2f", money));
            System.out.println("# of Actions: " + actions);
            System.out.println();
            menu.addItem();
            System.out.println();
            menu.printMenu();
            for (int i = 0; i < actions; i++) {
                choice();
            }


            day++;
        }
        if (money < 0) {
            System.out.println("You ran out of money and your food truck has gone out of business!");
        } else if (money >= 150) {
            System.out.println("Congratulations! Your food truck was a success and you hit your goal of $150!");
        } else {
            System.out.println("You couldn't make your budget in time and now you're poor and homeless.\n");
            sleep(3000);
            System.out.println("FOREVER");
        }
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public int getActions() {
        return actions;
    }
    private void choice() {
        System.out.println("Today, I'm going to(type name)");
        System.out.println("     [Work]    [Upgrade]");
        String choice = scan.nextLine();

        if (choice.toLowerCase().equals("work")) {
            work();
        }
        if (choice.toLowerCase().equals("upgrade")) {
            upgrade();
        }
    }
    private void work() {
        double orderCost = 0;
        int orders = (int) (Math.random() * 3) + (int) (Math.random() * menu.getMenu().size()) + 1;
        for (int i = orders ; i > 0; i--) {
            int random = (int) (Math.random() * menu.getMenu().size());
            orderCost += menu.getPrices().get(random);
        }
        orderCost *= upgrades.calculateBonus();
        System.out.println("You had " + orders + " orders. You made $" + orderCost + ".");
        money += orderCost;
    }
    public void upgrade() {
        money = upgrades.purchase(money);
    }
    private void sleep(long time) { // (1)
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Thread is interrupted");
            Thread.currentThread().interrupt();
        }
    }
}