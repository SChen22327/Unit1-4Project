import java.text.DecimalFormat;
import java.util.Scanner;
public class Upgrades {
    private Scanner scan;
    private String[] upgrades = {"Daily Actions", "Better Stove", "Appearance", "Better Supplier", "Cleaner Kitchen"};
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

    public double purchase(double money) {
        double cost;
        System.out.println("Upgrades(type the number of which upgrade)");
        for (int i = 0; i < upgrades.length; i++) {
            if (i == 0) {
                System.out.print("1. Daily Actions, Level: ");
                if (levels[0] == 2) {
                    System.out.print("MAXED");
                } else {
                    System.out.println(1);
                    cost = 5 + Math.round(Math.random() * levels[i] * 100) / 100.0;
                    System.out.println("  |  $" + (cost + 5));
                }
            } else {
                System.out.print(i + 1 + ". " + upgrades[i] + ", Level: ");
                if (levels[i] == 3) {
                    System.out.println("MAXED");
                } else {
                    cost = 5 + Math.round(Math.random() * levels[i] * 100) / 100.0;
                    System.out.println(levels[i] + "  |  $" + cost);
                }
            }
        }
        int choice = scan.nextInt();
        scan.nextLine();
        if (levels[choice] == 3 || (choice == 3 && levels[0] == 2)) {
            System.out.print("That upgrade has been maxed out. Enter another one: ");
            choice = scan.nextInt();
            scan.nextLine();
            System.out.println();
        }
        cost = 5 + Math.round(Math.random() * levels[choice] * 100) / 100.0;
        money -= cost;
        levels[choice] += 1;
        System.out.println(choice + ". " + upgrades[choice] + ", " + levels[choice]);
        return money;
    }
    public double calculateBonus() {
        int bonus = 0;
        for (int i = 0; i < levels.length; i++) {
            bonus += levels[i] - 1;
        }
        return 1 + bonus * 0.1;
    }
}
