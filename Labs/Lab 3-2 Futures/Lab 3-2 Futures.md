# Lab 3-2 Futures

## Lab Objectives

In this lab you will write some code to experiement with futures

## Part 1: A simple Future

- In this part, you are going to start a task that returns the String value "Mission Accomplished" when it finishes

This code also uses a single thread executor
- This is a common construct when we want to create a new thread and start it running
- This is a more modern version of creating a Thread object and calling start on it.
- In the code, we start a loop that keeps checking to see if the task is done
- When the task is done, it prints out the value in the Future.
- The code is available in the file `part1.java` in the Solutions folder


```java
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
```

Your output should look something like this:

```console
Submitting the thread job
Waiting for the result .....
Waiting for the result .....
Waiting for the result .....
Waiting for the result .....
Waiting for the result .....
Waiting for the result .....
Waiting for the result .....
Waiting for the result .....
Waiting for the result .....
Waiting for the result .....
The thread has completed
Mission Accomplished

Process finished with exit code 0
```

## Part 2: Multiple executions

In this section, we are creating a class called `Calculator` that calculates the square of number
- When the `Calculator` class is instantiated, it creates a single thread executor
- This executor creates a new thread each time it is called.
- When the `calculate` method is called, instead of executing in the main thread, the class executes the computation in its own private thread provided by the executor.

```java
class SquareCalculator {

    private final ExecutorService executor
            = Executors.newSingleThreadExecutor();

    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
}
```

- In the `Runner` class, we instantiate a calculator object
- Then we call the `calculate()` method twice 
- This causes two threads to start
- We wait loop until both are done and then print the results that are in the Future objects
- This code is available in the file `part2.jave` in the Solutions dierectory

```java
package pools;

import java.util.concurrent.*;
public class Runner {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        SquareCalculator squareCalculator = new SquareCalculator();

        Future<Integer> future1 = squareCalculator.calculate(10);
        Future<Integer> future2 = squareCalculator.calculate(100);

        while (!(future1.isDone() && future2.isDone())) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s",
                            future1.isDone() ? "done" : "not done",
                            future2.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        Integer result1 = future1.get();
        Integer result2 = future2.get();

        System.out.println(result1 + " and " + result2);

    }
    
}
```

Your output should look like this

```console
future1 is not done and future2 is not done
future1 is not done and future2 is not done
future1 is not done and future2 is not done
future1 is not done and future2 is not done
future1 is done and future2 is not done
future1 is done and future2 is not done
future1 is done and future2 is not done
100 and 10000
```

## End Lab