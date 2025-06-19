import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;



public class Runner {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {

            return "First Part";
        }).thenApply(data -> {
            // Intentionally throw an exception to demonstrate handling
            if (false) {
                throw new RuntimeException("Error in processing second part");
            }
            return "Second part using " + data;
        }).thenApply(data2 -> {
            return data2 + ", With some more stuff added on.";
        }).handle((result, exception) -> {
            if (exception != null) {
                System.out.println("Exception caught in handle(): " + exception.getMessage());
                return "Invalid Result";
            } else {
                return result + " (no exception)";
            }
        });

        System.out.println("Final result: " + welcomeText.get());
    }
}
