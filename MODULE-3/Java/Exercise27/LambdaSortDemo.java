import java.util.ArrayList;
import java.util.List;

public class LambdaSortDemo {

    public static void main(String[] args) {
        List<String> roster = new ArrayList<>();
        roster.add("Daniel");
        roster.add("Sophia");
        roster.add("James");
        roster.add("Emma");

        System.out.println("Original order:");
        for (String name : roster) {
            System.out.println(name);
        }

        System.out.println();

        roster.sort((x, y) -> x.compareToIgnoreCase(y));

        System.out.println("Sorted order:");
        for (String name : roster) {
            System.out.println(name);
        }
    }
}
