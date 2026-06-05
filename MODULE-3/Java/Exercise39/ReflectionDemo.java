import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Learner {

    public void presentInfo() {
        System.out.println("Showing learner information.");
    }

    public void calculateScore() {
        System.out.println("Score calculated successfully.");
    }
}

public class ReflectionDemo {

    public static void main(String[] args) {
        try {
            Class<?> target = Class.forName("Learner");
            System.out.println("Class resolved.");
            System.out.println();

            Method[] allMethods = target.getDeclaredMethods();
            System.out.println("Available methods:");
            for (Method m : allMethods) {
                System.out.println("  -> " + m.getName());
            }
            System.out.println();

            Constructor<?> constructor = target.getDeclaredConstructor();
            Object instance = constructor.newInstance();

            Method action = target.getMethod("presentInfo");
            System.out.println("Calling presentInfo():");
            System.out.println();
            action.invoke(instance);

        } catch (ClassNotFoundException e) {
            System.out.println("Missing class: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.out.println("Method not found: " + e.getMessage());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Reflection error: " + e.getMessage());
        }
    }
}
