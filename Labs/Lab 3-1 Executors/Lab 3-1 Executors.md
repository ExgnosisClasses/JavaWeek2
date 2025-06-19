# Lab 3-1 Executors


## Lab Objectives

The modern approach to multithreading is to use an `Executor` interface to delegate to the JVM the management of creating and running `Thread` objects rather extending a thread class or implementing a `Runnable` interface.


## Part 1: Create the Thread class

- Create a `pools` package to work in.
- Create a `Runnable` object that prints out information about itself
- The class implements `Runnable` but also prints out the name of the thread it is running in using a `Thread` static method. 
- The `Thread.sleep()` method to slow down the output so we can see what is happening.

```java
package pools;

class MyTask implements Runnable {

	public String name;

	public MyTask(String n) {
		this.name = n;
	}

	@Override
	public void run() {
		String message = Thread.currentThread().getName() + ": " + this.name;
		System.out.println(message);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e);			
		}
	}
}
```


## Part 2: Use a fixed size Executor

- Create a fixed size executor pool.
- We wrap the code in a try block to ensure that the executor is shut down correctly

```java
public class Runner {

    public static void main(String[] args) {

        ExecutorService myPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 1; i < 5; i++) {
                myPool.execute(new MyTask("Task " + i));
            }
        } finally {
            myPool.shutdown();
        }

    }
}

```

- Your results should look something like this

```console
pool-1-thread-2: Task 2
pool-1-thread-3: Task 3
pool-1-thread-1: Task 1
pool-1-thread-3: Task 4

Process finished with exit code 0
```

The code for this section is in the file `part1.java` in the solutions folder.

Experiment with the number of threads in the pool and the number of tasks created.

## Part 3: Use a configurable executor

Instead of using a fixed poole executor, modify the `Runner` class to use the configurable executor interface as in the following.
- The code for this is in `part2.java` in the Solutions directory

```java
package pools;

import java.util.concurrent.*;

public class Runner {

    public static void main(String[] args) {

        int corePoolSize = 3;
        int maxPoolSize = 5;
        long keepAliveTime = 3000;

        BlockingQueue<Runnable> pool = new ArrayBlockingQueue<Runnable>(30);
        ExecutorService myPool = new ThreadPoolExecutor(
                corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, pool);

        try {

            for (int i = 1; i < 10; i++) {
                myPool.execute(new MyTask("Task " + i));
            }
        } finally {
            myPool.shutdown();
        }
    }
}

```
Your output should look something like this.

```console
pool-1-thread-1: Task 1
pool-1-thread-2: Task 2
pool-1-thread-3: Task 3
pool-1-thread-1: Task 4
pool-1-thread-3: Task 5
pool-1-thread-2: Task 6
pool-1-thread-1: Task 7
pool-1-thread-3: Task 8
pool-1-thread-2: Task 9

Process finished with exit code 0
```
Run the code and notice that the three threads can handle all the jobs queued up.

Experiment with changing the size of the blocking queue to 3 and increasing the number of tasks to 10. 
- Can you explain why the exception is thrown in the resulting output?
- Hint: look at how many tasks were executed. Change the size of the blocking queue to 30 and see what happens.

```console
Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task pools.MyTask@38af3868 rejected from java.util.concurrent.ThreadPoolExecutor@23fc625e[Running, pool size = 5, active threads = 5, queued tasks = 3, completed tasks = 0]
	at java.base/java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2027)
	at java.base/java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:792)
	at java.base/java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1328)
	at pools.Runner.main(Runner.java:20)
pool-1-thread-5: Task 8
pool-1-thread-4: Task 7
pool-1-thread-2: Task 2
pool-1-thread-1: Task 1
pool-1-thread-3: Task 3
pool-1-thread-3: Task 4
pool-1-thread-1: Task 6
pool-1-thread-5: Task 5

Process finished with exit code 1
```

## End Lab
  