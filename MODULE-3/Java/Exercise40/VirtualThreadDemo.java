import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VirtualThreadDemo {

    private static final int TOTAL_TASKS = 100_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Spawning virtual threads...");
        System.out.println();

        long start = System.currentTimeMillis();

        try (ExecutorService pool = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < TOTAL_TASKS; i++) {
                pool.submit(() -> {
                    System.out.println("Task completed.");
                });
            }
        }

        long elapsed = System.currentTimeMillis() - start;

        System.out.println();
        System.out.println("Every thread finished.");
        System.out.println();
        System.out.println("Duration: " + elapsed + " ms");
        System.out.println("Created " + TOTAL_TASKS + " virtual threads.");
        System.out.println("Platform threads would consume far more resources.");
    }
}
