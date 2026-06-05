public class ThreadDemo {

    public static void main(String[] args) {
        Thread recordWorker = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Record processing active");
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        Thread reportWorker = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Report generation running");
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        recordWorker.start();
        reportWorker.start();

        try {
            recordWorker.join();
            reportWorker.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
