import java.util.concurrent.*;

public class Runner {
    public static void main(String args[]) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        System.out.println("Submitting the thread job");
        Future<String> future = es.submit(() -> {
            Thread.sleep(2000);
            return "Mission Accomplished";
        });
        while (!future.isDone()) {
            System.out.println("Waiting for the result .....");
            Thread.sleep(200);
        }
        System.out.println("The thread has completed");
        System.out.println(future.get());
        es.shutdown();
    }
}