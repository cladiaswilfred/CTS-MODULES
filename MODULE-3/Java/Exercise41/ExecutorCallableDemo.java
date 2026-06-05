import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorCallableDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        List<Future<String>> results = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            final int taskId = i;

            Callable<String> worker = () -> {
                Thread.sleep(200);
                System.out.println("Job " + taskId + " completed.");
                return "Result OK for task " + taskId;
            };

            results.add(threadPool.submit(worker));
        }

        System.out.println();
        System.out.println("Collected outputs:");
        System.out.println();

        for (Future<String> f : results) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                System.out.println("Error in task: " + e.getCause().getMessage());
            }
        }

        threadPool.shutdown();
    }
}
