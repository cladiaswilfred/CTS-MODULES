import java.util.Scanner;

public class ArraySumAverage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of elements: ");
        if (scanner.hasNextInt()) {
            int size = scanner.nextInt();
            System.out.println();

            if (size <= 0) {
                System.out.println("Size must be greater than zero.");
            } else {
                int[] arr = new int[size];
                int sum = 0;

                System.out.println("Enter " + size + " numbers:");
                for (int idx = 0; idx < size; idx++) {
                    if (scanner.hasNextInt()) {
                        arr[idx] = scanner.nextInt();
                        sum += arr[idx];
                    } else {
                        System.out.println("Integers only. Exiting.");
                        scanner.close();
                        return;
                    }
                }

                double average = (double) sum / size;

                System.out.println();
                System.out.println("Sum:      " + sum);
                System.out.println("Average:  " + average);
            }
        } else {
            System.out.println("Enter a valid integer.");
        }

        scanner.close();
    }
}
