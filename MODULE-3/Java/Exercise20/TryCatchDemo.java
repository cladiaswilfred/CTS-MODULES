import java.util.Scanner;

public class TryCatchDemo {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        System.out.print("Top number: ");
        if (reader.hasNextInt()) {
            int a = reader.nextInt();

            System.out.print("Bottom number: ");
            if (reader.hasNextInt()) {
                int b = reader.nextInt();
                System.out.println();

                try {
                    int result = a / b;
                    System.out.println(a + " / " + b + " = " + result);
                } catch (ArithmeticException e) {
                    System.out.println("Cannot divide by zero.");
                }
            } else {
                System.out.println("Bottom must be an integer.");
            }
        } else {
            System.out.println("Top must be an integer.");
        }

        reader.close();
    }
}
