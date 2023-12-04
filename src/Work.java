/*  (1) https://www.javaprogramto.com/2020/08/how-to-make-delay-in-java-thread-sleep.html
    (2) https://www.w3schools.io/java/java15-text-blocks/
    (3)
 */
import java.io.IOException;
import java.util.Scanner;
public class Work {
    //A lot of instance variables
    private double money;
    private int maxDay, actions, goal;
    private String name, ftName;
    private Scanner scan;
    private Upgrades upgrades;
    private Menu menu;
    //overloaded and regular constructors
    public Work(Scanner scan, String name) {
        money = 0;
        this.name = name;
        this.scan = scan;
        maxDay = 8;
        goal = 300;
        System.out.println("Do you want to play in easy mode?");
        System.out.println("All upgrades except actions will start at level 2.");
        System.out.print("Enter 1 for easy mode, 2 for normal mode: ");
        String n = "silly placeholder";
        while (!(n.equals("1") || n.equals("2"))) {
            n = scan.nextLine();
            if (n.equals("1")) {
                upgrades = new Upgrades(scan, true);
            } else if (n.equals("2")) {
                upgrades = new Upgrades(scan, false);
            } else {
                System.out.print("That's not a valid option, try again: ");
            }
        }
        System.out.println("Demo mode decreases current goal by $50 and the number of days by 2.");
        System.out.print("For demo mode, type 123. Type anything else to ignore: ");
        String demo = scan.nextLine();
        if (demo.equals("123")) {
            System.out.println("Demo mode selected.");
            goal -= 50;
            maxDay -= 2;
        }
        menu = new Menu(scan);
        actions = 1;

    }
    public Work(Scanner scan, String name, int goal) {
        money = 0;
        this.name = name;
        this.scan = scan;
        maxDay = 8;
        System.out.println("Do you want to play in easy mode?");
        System.out.println("All upgrades except actions will start at level 2.");
        System.out.print("Enter 1 for easy mode, 2 for normal mode: ");
        String n = "silly placeholder";
        while (!(n.equals("1") || n.equals("2"))) {
            n = scan.nextLine();
            if (n.equals("1")) {
                upgrades = new Upgrades(scan, true);
            } else if (n.equals("2")) {
                upgrades = new Upgrades(scan, false);
            } else {
                System.out.print("That's not a valid option, try again: ");
            }
        }
        this.goal = goal;
        System.out.println("Demo mode decreases current goal by $50 and the number of days by 2.");
        System.out.print("For demo mode, type 123. Type anything else to ignore: ");
        String demo = scan.nextLine();
        if (demo.equals("123")) {
            System.out.println("Demo mode selected.");
            this.goal -= 50;
            maxDay -= 2;
        }
        menu = new Menu(scan);
        actions = 1;
    }
    //start method for game
    public void start() {
        int day = 1;
        System.out.print("Enter a name for your new food truck: ");
        ftName = scan.nextLine();
        System.out.println();
        System.out.printf("""
                ⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻
                The rules of this game are
                  1. You have %1$s days/rounds to earn $%2$s.
                  2. Every day, you will be asked to add an item to the menu. You can give it a price OR make it free.
                  3. You can buy upgrades which will greatly increase the money you earn but decreases the amount of
                     money you have(see rule 6).
                  4. You can work each day to earn money. Any upgrades will be applied at the end of each work action.
                  5. You can ONLY choose to work or upgrade each day, you can't do both.
                      a. You can increase your actions by 1, allowing you to do two things each day.
                      b. If you choose to upgrade, you may only choose to upgrade one thing.
                      c. Money made somewhat depends on the number of items on your menu, which increases by 1 each day,
                         meaning you'll likely earn more later on.
                  6. If you fail to earn enough money or run out of money, you will lose.""", maxDay, goal); //(2)
        while (day != maxDay && money >= 0) {          //THIS IS THE WORK DAY LOOP//
            System.out.println("\n⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻");
            System.out.println("\nDay " + day + " of " + name + "'s food truck");
            System.out.println("Current Balance: $" + String.format("%.2f", money));
            if (upgrades.incrementActions(actions)) {
                actions++;
            }
            System.out.println("# of Actions: " + actions);
            System.out.println("Goal: " + goal);
            System.out.println("\nWould you like to make this next item free? Yes for free, no for adding a price.");
            System.out.print("Be warned that this may affect your earnings: ");
            String y = "nonsense placeholder or something???";
            while (!(y.equalsIgnoreCase("yes") || y.equalsIgnoreCase("no"))) {
                y = scan.nextLine();
                if (y.equalsIgnoreCase("yes")) {
                    System.out.println("You chose to favor the poor and made it free.");
                    System.out.print("Enter item to make free(I suggest water): ");
                    String item = scan.nextLine();
                    menu.addItem(item);
                } else if (y.equalsIgnoreCase("no")) {
                    System.out.println("You chose to give it a price.");
                    menu.addItem();
                } else {
                    System.out.print("That's not an option! It's yes or no, try again but do it better: ");
                }
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
        System.out.printf("The %s days are over, let's see how \"" + ftName + "\" did.\n", maxDay - 1);
        System.out.println("Balance: " + String.format("%.2f", money));
        System.out.println("Goal to reach: " + goal);
        menu.printMenu();
        if (money < 0) {
            System.out.println("You ran out of money and your food truck has gone out of business! What a failure...");
            for (int i  = 0; i < menu.getPrices().size(); i++) {
                if (menu.getPrices().get(i) == 0) {
                    System.out.print("At least the people are happy you made something on your menu free for the poor...");
                    System.out.println("Rating: 1/5 stars");
                    break;
                }
            }
        } else if (money >= goal) {
            System.out.println("Congratulations! Your food truck was a success and you hit your goal of $300!");
            System.out.println("Rating: 5/5 stars, food truck royalty\uD83D\uDC51");
        } else {
            System.out.println("You couldn't make your budget in time and now you're POOR and HOMELESS.");
            System.out.println("Pause for dramatic effect");
            sleep(700);
            for (int i = 0; i < 3; i++) {
                System.out.println(".");
                sleep(700);
            }
            System.out.println("FOREVER\nRating: YOU DON'T DESERVE ONE");
        }
    }
    //Asks user to work or upgrade, running the corresponding method
    private void choice() {
        System.out.println("\"Today, I'm going to: \"     (type name)");
        System.out.println("     [Work]    [Upgrade]");
        String choice = scan.nextLine();
        System.out.println();
        if (choice.equalsIgnoreCase("work")) {
            work();
        } else if (choice.equalsIgnoreCase("upgrade")) {
            upgrade();
        } else {
            System.out.println("That's not an option!");
            choice();
        }
    }
    //Calculates money earned and bonuses
    private void work() {
        double bonus = upgrades.calculateBonus();
        double orderCost = 0;
        int orders = (int) (Math.random() * 3) + (int) (Math.random() * menu.getMenu().size()) + 1;
        for (int i = orders ; i > 0; i--) {
            int random = (int) (Math.random() * menu.getMenu().size());
            orderCost += menu.getPrices().get(random);
        }
        orderCost = Math.round(orderCost * bonus * 100) / 100.0;
        System.out.println("Working...");
        System.out.println("\nYou had " + orders + " orders. With current level of upgrades(+" +
                Math.round((bonus - 1) * 100) + "%), you made $" + String.format("%.2f",orderCost) + ".");
        money += orderCost;
    }
    /*Runs the purchase method in the Upgrades class, decreasing the user's balance by the money used to purchase an upgrade
      Also has an "exit" in case the user decides not to upgrade, work doesn't have one*/
    public void upgrade() {
        double temp = upgrades.purchase(money);
        if (temp == -1) {
            choice();
        } else {
            money = temp;
        }
    }
    //Just delays the code for *dramatic effect*
    private void sleep(long time) { // (1) just here so I can make losing have a dramatic effect
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Thread is interrupted");
            Thread.currentThread().interrupt();
        }
    }
    //Removes redundancy in Runner class
    public static int askGoal(Scanner scan) {
        int goal = scan.nextInt();
        scan.nextLine();
        return goal;
    }
}