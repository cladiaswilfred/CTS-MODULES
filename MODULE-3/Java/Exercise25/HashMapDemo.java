import java.util.Scanner;
import java.util.TreeMap;

public class HashMapDemo {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TreeMap<Integer, String> students = new TreeMap<>();

        System.out.print("Enter student ID: ");
        if (input.hasNextInt()) {
            int id = input.nextInt();
            input.nextLine();

            System.out.print("Enter full name: ");
            String name = input.nextLine();

            students.put(id, name);
            System.out.println();

            System.out.print("Lookup ID: ");
            if (input.hasNextInt()) {
                int target = input.nextInt();
                System.out.println();

                String found = students.get(target);
                if (found != null) {
                    System.out.println("Student: " + found);
                } else {
                    System.out.println("ID " + target + " not found.");
                }
            }
        }

        input.close();
    }
}
