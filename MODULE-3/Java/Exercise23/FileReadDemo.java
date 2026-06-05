import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReadDemo {

    public static void main(String[] args) {
        String fileName = "output.txt";
        Path localPath = Paths.get(fileName);

        if (!Files.exists(localPath)) {
            localPath = Paths.get("../Exercise22/output.txt");
        }

        System.out.println("Loading " + fileName);
        System.out.println();

        try {
            List<String> lines = Files.readAllLines(localPath);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.println("Could not read file: " + ex.getMessage());
            System.out.println("Make sure Exercise22 was executed first.");
        }
    }
}
