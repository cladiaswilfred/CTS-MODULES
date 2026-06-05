import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which number? ");
        int val = scanner.nextInt();

        System.out.println();
        System.out.println("=== " + val + " Times Table ===");
        int counter = 1;
        while (counter <= 10) {
            System.out.printf("%d x %d = %d%n", val, counter, val * counter);
            counter++;
        }

        scanner.close();
    }
}
