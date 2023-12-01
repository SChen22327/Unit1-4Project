import java.util.Scanner;
public class Runner {
    public static void main(String[] args) {
        Work job;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter player name: ");
        String name = scan.nextLine();
        System.out.print("Enter a goal of at least $250(no cents), enter -1 if you want to use default goal of $300: ");
        int goal = scan.nextInt();
        scan.nextLine();
        while (goal < 250 && goal != -1) {
            System.out.print("Enter a goal that's AT LEAST $250: $");
            goal = scan.nextInt();
            scan.nextLine();
        }
        if (goal != -1) {
            job = new Work(scan, name, goal);
        } else {
            job = new Work(scan, name);
        }
        job.start();
    }
}
