import java.util.Scanner;

public class FactorialCalculator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Number: ");
        if (scan.hasNextInt()) {
            int num = scan.nextInt();
            System.out.println();

            if (num < 0) {
                System.out.println("Factorial undefined for negative numbers.");
            } else {
                long result = 1L;
                int i = 1;
                while (i <= num) {
                    result *= i;
                    i++;
                }
                System.out.println(num + "! = " + result);
            }
        } else {
            System.out.println("That is not a number.");
        }

        scan.close();
    }
}
