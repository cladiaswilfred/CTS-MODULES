import java.util.Scanner;

public class SimpleCalculator {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("== SIMPLE CALC ==");
        System.out.print("First number: ");
        double x = scan.nextDouble();

        System.out.print("Second number: ");
        double y = scan.nextDouble();

        System.out.println("1 - Add");
        System.out.println("2 - Subtract");
        System.out.println("3 - Multiply");
        System.out.println("4 - Divide");
        System.out.print("Choose (1-4): ");
        int cmd = scan.nextInt();

        System.out.println("--- RESULT ---");

        if (cmd == 1) {
            System.out.printf("Sum: %.2f%n", x + y);
        } else if (cmd == 2) {
            System.out.printf("Difference: %.2f%n", x - y);
        } else if (cmd == 3) {
            System.out.printf("Product: %.2f%n", x * y);
        } else if (cmd == 4) {
            if (y == 0) {
                System.out.println("Division by zero not allowed.");
            } else {
                System.out.printf("Quotient: %.2f%n", x / y);
            }
        } else {
            System.out.println("Invalid choice.");
        }

        System.out.println("================");
        scan.close();
    }
}
