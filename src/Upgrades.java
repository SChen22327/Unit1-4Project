import java.text.DecimalFormat;
import java.util.Scanner;
public class Upgrades {
    private Scanner scan;
    private String[] upgrades = {"Daily Actions", "Stove"};
    private double[] levels = {1, 1};
    public Upgrades(Scanner scan) {
       this.scan = scan;
    }

    public void purchase(double money) {
        double cost;
        System.out.println("Upgrades(type the number of which upgrade)");
        for (int i = 0; i < upgrades.length; i++) {
            if (i == 0) {
                if (levels[0] == 2) {
                    System.out.println("1. Daily Actions, MAXED");
                }
            } else {
                System.out.print(i + ". " + upgrades[i] + ", ");
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
        levels[choice] += 0.1;
        System.out.println(choice + ". " + upgrades[choice] + ", " + levels[choice]);
    }
    private double getLevels(int index) {
        return levels[index]; //JEEP VOIWORKGIB OBN TGUIS
    }
}
