import java.text.DecimalFormat;
import java.util.Scanner;
public class Upgrades {
    private Scanner scan;
    private String[] upgrades = {"Daily Actions", "Better Stove", "Better Appearance", "Better Supplier", "Cleaner Kitchen"};
    private int[] levels = {1, 1, 1, 1, 1};
    public Upgrades(Scanner scan) {
       this.scan = scan;
    }
    public Upgrades(Scanner scan, boolean easy) {
        this.scan = scan;
        for (int i = 1; i < levels.length; i++) {
            levels[i]++;
        }
    }
    /*Used in the Work class
      It prints each of the upgrades, their current levels, and prices(if not maxed already)
      User chooses one and that upgrade's level increases by 1
     */
    public double purchase(double money) {
        double cost;
        System.out.println("Upgrades(type the number of which upgrade)");
        for (int i = 0; i < levels.length; i++) {
            if (i == 0) {
                System.out.print("1. Daily Actions, Level: ");
                if (levels[0] == 2) {
                    System.out.println("MAXED");
                } else {
                    System.out.print(1);
                    cost = 20 + Math.round(0.43 * levels[i] * 100) / 100.0;
                    System.out.println("  |  $" + (cost));
                }
            } else {
                System.out.print(i + 1 + ". " + upgrades[i] + ", Level: ");
                if (levels[i] == 3) {
                    System.out.println("MAXED");
                } else {
                    cost = 5 + Math.round(0.43 * levels[i] * 100) / 100.0;
                    System.out.println(levels[i] + "  |  $" + cost);
                }
            }
        }
        System.out.println("6. EXIT UPGRADE");
        System.out.print("Upgrade(1-6): ");
        int choice = scan.nextInt();
        scan.nextLine();
        choice--;
        if (choice == 5) {
            return -1;
        }
        while (choice > 4 || levels[choice] == 3 || (choice == 0 && levels[0] == 2)) {
            if (choice > 4) {
                System.out.print("That is an invalid choice. Enter another one: ");
            } else {
                System.out.print("That upgrade has been maxed out. Enter another one: ");
            }
            choice = scan.nextInt();
            scan.nextLine();
            choice--;
            System.out.println();
        }
        if (choice == 0) {
            cost = 20.43;
        } else {
            cost = 5 + Math.round(0.43 * levels[choice] * 100) / 100.0;
        }
        money -= cost;
        levels[choice] += 1;
        System.out.println();
        System.out.println(choice + 1 + ". " + upgrades[choice] + ", Level: " + levels[choice]);
        return money;
    }
    //Calculates the bonus that the user has based off the levels of the upgrades
    public double calculateBonus() {
        int bonus = 0;
        for (int i = 0; i < levels.length; i++) {
            bonus += levels[i] - 1;
        }
        return 1 + bonus * 0.1;
    }
    //Used to increase actions in the Work class
    public boolean incrementActions(int lvl) {
        return lvl < levels[0];
    }
}
