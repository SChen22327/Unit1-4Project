import java.util.Scanner;
public class Work {
    private double money;
    private String name;
    private Scanner scan;
    private String ftName;
    private int day = 1;
    public Work(Scanner scan, String name) {
        money = 0;
        this.name = name;
        this.scan = scan;
    }

    public void start() {
        Upgrades upgrades = new Upgrades(money, scan);
        System.out.print("Enter your food truck's name: ");
        ftName = scan.nextLine();

        while (day != 8) {
            System.out.println("\nDay " + day + " of " + name + "'s food truck");










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

    }
    public void upgrade() {

    }
}
