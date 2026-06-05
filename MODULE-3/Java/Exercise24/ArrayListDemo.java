import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ArrayListDemo {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        System.out.print("How many students? ");
        if (reader.hasNextInt()) {
            int count = reader.nextInt();
            reader.nextLine();
            System.out.println();

            if (count < 1) {
                System.out.println("Count must be positive.");
            } else {
                List<String> students = new LinkedList<>();

                System.out.println("Type each name:");
                for (int i = 1; i <= count; i++) {
                    students.add(reader.nextLine());
                }

                System.out.println();
                System.out.println("Class list:");
                int index = 1;
                for (String s : students) {
                    System.out.println(index + ". " + s);
                    index++;
                }
            }
        } else {
            System.out.println("Invalid number.");
        }

        reader.close();
    }
}
