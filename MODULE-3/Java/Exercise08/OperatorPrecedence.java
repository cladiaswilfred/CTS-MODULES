public class OperatorPrecedence {

    public static void main(String[] args) {

        int a = 10 + 4 * 3;
        System.out.println("10 + 4 * 3            = " + a);

        int b = (10 + 4) * 3;
        System.out.println("(10 + 4) * 3          = " + b);

        int c = 20 / 4 - 2;
        System.out.println("20 / 4 - 2            = " + c);

        int d = 15 - 9 % 4;
        System.out.println("15 - 9 %% 4            = " + d);

        System.out.println();
        System.out.println("*, /, %% have higher precedence than +, -.");
    }
}
