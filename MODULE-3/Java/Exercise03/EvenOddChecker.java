import java.util.Scanner;

public class EvenOddChecker {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int num = reader.nextInt();

        String result = (num % 2 == 0) ? "even" : "odd";
        System.out.println(num + " is " + result + ".");

        reader.close();
    }
}
