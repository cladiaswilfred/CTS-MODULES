import java.util.Scanner;

public class StringReversal {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Type something: ");
        if (input.hasNextLine()) {
            String text = input.nextLine();
            char[] chars = text.toCharArray();
            int left = 0;
            int right = chars.length - 1;

            while (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }

            String reversed = new String(chars);

            System.out.println();
            System.out.println("Original: " + text);
            System.out.println("Reversed: " + reversed);
        } else {
            System.out.println("No input.");
        }

        input.close();
    }
}
