public class PatternMatchingDemo {

    public static void identify(Object obj) {
        if (obj instanceof Integer i) {
            System.out.println("Integer value: " + i);
        } else if (obj instanceof String s) {
            System.out.println("String value: " + s);
        } else if (obj instanceof Double d) {
            System.out.println("Double value: " + d);
        } else if (obj instanceof Boolean b) {
            System.out.println("Boolean value: " + b);
        } else if (obj == null) {
            System.out.println("Object is null");
        } else {
            System.out.println("Unrecognized type: " + obj);
        }
    }

    public static void main(String[] args) {
        identify(99);
        identify("Teacher");
        identify(45.3);
        identify(true);
        identify(null);
    }
}
