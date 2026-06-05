import java.util.Scanner;

public class RecursiveFibonacci {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter n: ");
        if (in.hasNextInt()) {
            int n = in.nextInt();
            System.out.println();

            if (n < 1) {
                System.out.println("Positive numbers only.");
            } else {
                int answer = fibonacci(n);
                System.out.println("fib(" + n + ") = " + answer);
            }
        } else {
            System.out.println("Invalid input.");
        }

        in.close();
    }

    public static int fibonacci(int pos) {
        if (pos <= 2) {
            return pos - 1;
        }
        return fibonacci(pos - 1) + fibonacci(pos - 2);
    }
}
