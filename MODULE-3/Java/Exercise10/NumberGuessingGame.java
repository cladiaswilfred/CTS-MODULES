import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Random gen = new Random();

        int hidden = gen.nextInt(100) + 1;
        int attempts = 0;

        System.out.println("I picked a number between 1 and 100. Guess it!");

        while (true) {
            System.out.print("Guess: ");
            if (reader.hasNextInt()) {
                int guess = reader.nextInt();
                attempts++;

                if (guess == hidden) {
                    System.out.println("Correct! You got it in " + attempts + " tries.");
                    break;
                } else if (guess < hidden) {
                    System.out.println("Higher.");
                } else {
                    System.out.println("Lower.");
                }
            } else {
                System.out.println("Invalid input. Numbers only.");
                reader.next();
            }
        }

        reader.close();
    }
}
