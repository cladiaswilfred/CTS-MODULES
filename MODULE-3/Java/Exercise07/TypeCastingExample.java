public class TypeCastingExample {

    public static void main(String[] args) {

        double original = 77.77;
        int castedValue = (int) original;
        System.out.println("Before cast (double): " + original);
        System.out.println("After cast (int):    " + castedValue);
        System.out.println();

        int source = 100;
        double target = source;
        System.out.println("Before widen (int):    " + source);
        System.out.println("After widen (double):  " + target);
    }
}
