public class MethodOverloading {

    public static int sum(int a, int b) {
        return a + b;
    }

    public static double sum(double a, double b) {
        return a + b;
    }

    public static int sum(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {

        System.out.println("sum(10, 20)         = " + sum(10, 20));
        System.out.println("sum(1.5, 2.3)       = " + sum(1.5, 2.3));
        System.out.println("sum(3, 5, 7)        = " + sum(3, 5, 7));
    }
}
