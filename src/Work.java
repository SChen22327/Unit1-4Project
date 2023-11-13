import java.util.Scanner;
public class Work {
    private double money;
    private Scanner scan;
    private int day = 1;
    public Work() {
        money = 0;
        scan = new Scanner(System.in);
    }

    public void start() {
        Upgrades upgrades = new Upgrades(money);
        while (day != 8) {
            System.out.println("\nDay " + day);










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
