public class DecompileDemo {

    private String name;
    private int age;

    public DecompileDemo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void printDetails() {
        System.out.println("Student: " + name);
        System.out.println("Age    : " + age);
    }

    public boolean isEligible() {
        return age >= 18;
    }

    public static void main(String[] args) {
        DecompileDemo student = new DecompileDemo("Bob", 22);

        System.out.println("Java source code");
        System.out.println("      |");
        System.out.println("javac compiles");
        System.out.println("      |");
        System.out.println("Bytecode (.class)");
        System.out.println("      |");
        System.out.println("Decompiler reverses");
        System.out.println("      |");
        System.out.println("Reconstructed code");
        System.out.println();

        student.printDetails();
        System.out.println("Can enroll: " + student.isEligible());
        System.out.println();
        System.out.println("Note: Logic remains intact after decompilation.");
    }
}
