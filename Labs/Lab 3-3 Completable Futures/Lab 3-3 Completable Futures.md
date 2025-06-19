# Lab 3-3: Completable Futures

## Lab Objectives

In this lab, you will create a pipeline using completable futures


## Part 1: Basic Pipeline

In this section, we create a basi pipeline that builds up a final string from various steps
- The first step returns the string "First Part"
- The second step returns the result of the first part with "Second Part using " prepended 
- The third step returns the result of the second part with ", With some more stuff added on." appended
- The code for this is in the file `part1.java`


```java

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class Runner {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            return "First Part";
        }).thenApply(data -> {
            return "Second part using " + data;
        }).thenApply(data2 -> {
            return data2 + ", With some more stuff added on.";
        });

        System.out.println(welcomeText.get());
    }
}
```

Your output should look like this

```console
Second part using First Part, With some more stuff added on.

Process finished with exit code 0
```

## Part 2: Add error handling

In this section, we add error handline using the .hande() step.

- The .handle step is always executed
- If no exception is thrown, it just returns the result passed. 
- In our case, we add the notation " (no exception)" to show it executes
- But an exception does happen, the pipeline stops immediately and the .handle() executes recovery code, in case of returning an "Invalid Result" String
- Run the code below
- This code is in the `part2.java` file in the Solutions folder

```java

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

```

- Your output should look like this

```console
Final result: Second part using First Part, With some more stuff added on. (no exception)

Process finished with exit code 0
```

Change the `false` to `true` and rerun.  You should see the following

```console
Exception caught in handle(): java.lang.RuntimeException: Error in processing second part
Final result: Invalid Result

Process finished with exit code 0
```


## End Lab