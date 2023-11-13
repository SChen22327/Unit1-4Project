import java.util.Scanner;
public class Runner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter player name: ");
        String name = scan.nextLine();
        Work job = new Work(scan, name);
        job.start();
    }
}
