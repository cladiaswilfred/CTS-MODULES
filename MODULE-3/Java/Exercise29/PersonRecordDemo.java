import java.util.ArrayList;
import java.util.List;

public class PersonRecordDemo {

    record Student(String firstName, int years, String course) {}

    public static void main(String[] args) {
        List<Student> pupils = new ArrayList<>();
        pupils.add(new Student("John", 20, "Math"));
        pupils.add(new Student("Alex", 17, "Physics"));
        pupils.add(new Student("Maria", 22, "Chemistry"));

        System.out.println("Enrolled students:");
        for (Student s : pupils) {
            System.out.println(s.firstName() + " | " + s.years() + " | " + s.course());
        }
        System.out.println();

        System.out.println("Adult students (18+):");
        pupils.stream()
              .filter(p -> p.years() >= 18)
              .forEach(p -> System.out.println(p.firstName()));
    }
}
