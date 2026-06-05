public class BytecodeDemo {

    public int multiply(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        BytecodeDemo demo = new BytecodeDemo();
        int output = demo.multiply(7, 8);

        System.out.println("=== Bytecode Inspection ===");
        System.out.println();
        System.out.println("multiply(int, int):");
        System.out.println("  iload_1 -> load first param");
        System.out.println("  iload_2 -> load second param");
        System.out.println("  imul    -> multiply values");
        System.out.println("  ireturn -> send back result");
        System.out.println();
        System.out.println("7 * 8 = " + output);
        System.out.println();
        System.out.println("View with: javap -c BytecodeDemo");
    }
}
