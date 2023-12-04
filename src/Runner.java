import java.util.Scanner;
public class Runner {
    public static void main(String[] args) {
        Work job;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter player name: ");
        String name = scan.nextLine();
        System.out.print("Enter a goal(no cents) of at least $250, or -1 for default goal of $300: ");
        int goal = Work.askGoal(scan);
        while (goal < 250 && goal != -1) {
            System.out.print("Enter a goal that's AT LEAST $250 or -1 for $300: ");
            goal = Work.askGoal(scan);
        }
        if (goal == -1 || goal == 300) {
            job = new Work(scan, name);
        } else {
            job = new Work(scan, name, goal);
        }
        job.start();
    }
}