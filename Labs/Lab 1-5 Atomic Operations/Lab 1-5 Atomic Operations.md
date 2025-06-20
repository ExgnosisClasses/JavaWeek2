# Lab 1-5 Atomic Operations

## Lab Objectives

In this lab, we will use an `AtomicLong` class to implement the multithreaded counter we saw in Lab 1-3, but this time we let Java do all the work instead an explicit mutex

The Counter class is shown below and is in the file `Counter.java` in the Solutions folder.
- The only difference between this and the race condition counter is that we have replaced the long primitive value for count with an AtomicLong object
- The increment and decrement now use the corresponding methods on the AtomicCounter class


```java
class Counter implements Runnable {
   private static final AtomicLong count = new AtomicLong(0);

   public long increment() {
       try {
       Thread.sleep(100);
   } catch (Exception e) {
       System.err.println(e);
   }

       return Counter.count.incrementAndGet();
   }

   public long decrement() {
       return Counter.count.decrementAndGet();
   }

   public long getCount() {
       return Counter.count.get();
   }

    @Override
    public void run() {
       this.increment();
       this.decrement();

    }
}

```

The runner looks the same except for the creation of the Counter object.
- The code is in the file `Runner.java` in the Solutions folder


```java
import java.util.concurrent.atomic.AtomicLong;

public class Runner {
    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(counter, "Thread " + i);
            t.start();

        }

        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("Final Value = " + counter.getCount());
    }
}

```
 Run the code with a large number of threads and notice that we wind up with correct value each time


## End Lab
