import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class AgeLimitException extends Exception {
    public AgeLimitException(String message) {
        super(message);
    }
}

public class CustomExceptionDemo {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter your age: ");
        String input = reader.readLine();

        try {
            int age = Integer.parseInt(input);
            validateAge(age);
            System.out.println("Access granted - age requirement met.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a numeric value.");
        } catch (AgeLimitException e) {
            System.out.println("Blocked: " + e.getMessage());
        }

        reader.close();
    }

    public static void validateAge(int years) throws AgeLimitException {
        if (years < 18) {
            throw new AgeLimitException("Minimum age is 18 years.");
        }
    }
}
