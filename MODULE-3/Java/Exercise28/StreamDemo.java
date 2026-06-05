import java.util.ArrayList;
import java.util.List;

public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> codes = new ArrayList<>();
        codes.add(201);
        codes.add(202);
        codes.add(203);
        codes.add(204);

        System.out.println("Original IDs:");
        codes.forEach(c -> System.out.println(c));
        System.out.println();

        List<Integer> filtered = new ArrayList<>();
        codes.stream()
             .filter(n -> n % 2 == 0)
             .forEach(n -> filtered.add(n));

        System.out.println("Even IDs:");
        filtered.forEach(c -> System.out.println(c));
    }
}
