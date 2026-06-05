import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileWriteDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student information:");
        if (scanner.hasNextLine()) {
            String content = scanner.nextLine();
            String filePath = "output.txt";

            try {
                Files.writeString(Paths.get(filePath), content);
                System.out.println();
                System.out.println("Data written to " + filePath);
            } catch (IOException ex) {
                System.out.println("Failed to write: " + ex.getMessage());
            }
        }

        scanner.close();
    }
}
