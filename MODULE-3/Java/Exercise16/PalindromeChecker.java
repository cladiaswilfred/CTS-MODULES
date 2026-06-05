import java.util.Scanner;

public class PalindromeChecker {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        System.out.println("Enter text:");
        if (reader.hasNextLine()) {
            String phrase = reader.nextLine();
            String clean = phrase.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

            boolean isPalindrome = true;
            int i = 0;
            int j = clean.length() - 1;

            while (i < j) {
                if (clean.charAt(i) != clean.charAt(j)) {
                    isPalindrome = false;
                    break;
                }
                i++;
                j--;
            }

            System.out.println();
            if (isPalindrome && clean.length() > 0) {
                System.out.println("\"" + phrase + "\" is a palindrome.");
            } else {
                System.out.println("\"" + phrase + "\" is not a palindrome.");
            }
        }

        reader.close();
    }
}
