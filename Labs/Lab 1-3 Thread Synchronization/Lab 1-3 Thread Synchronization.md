# Lab 1-3 Thread Synchronizations



## Lab Overview

In this lab, you will create a race condition with multiple threads accessing a shared resource. In part two, you will use the synchronized keyword to manage the shared resource

---

## Part 1: Race Condition

This part of the lab demonstrates how an object created withing a thead is not shared by other threads

### Step 1: Counter Definition 

- The Counter class contains a counter with methods to increment and decrement the counter
- The Counter will be shared by 10 threads 
- The Counter is initially be set to zero
- Since each thread adds 1 then subtracts 1 from the counter, after all of the threads have run, the counter should be back to 0.
- You can add this code to the file where the `Runner` class is defined
- The code is available in the `Code` directory in the lab folder

```java
class Counter implements Runnable {
    private int myCount = 0;

    public void increment() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.err.println(e);
        }
        this.myCount++;
    }

    public void decrement() {
        this.myCount--;
    }

    public int getValue() {
        return this.myCount;
    }

    @Override
    public void run() {
        this.increment();
        this.decrement();
        // Uncomment out the line below if you want to see the value of the counter after each thread executes
       // System.out.println("Final value for Thread " + Thread.currentThread().getName() + " " + this.getValue());

    }
}
```

### Step 2: The Runner Class

- Run the code with the following `Runner` class code
- It creates 10 threads that all access the Counter object
- Remember, since the reference to the Counter object is passed to the thread, they all share the same Counter object
- The main thread sleeps for a while and then prints out the final result
- You will not get a race condition every single time you run the code.
- You can adjust the amount the threads sleep and the number of threads as an experiment
- The code is in the file `Runner.java` in the `Code` folder

```java
public class Runner {
    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 80; i++) {
            Thread t = new Thread(counter, "Thread " + i);
            t.start();

        }

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("Final Value = " + counter.getValue());
    }
}

```
- You should see output like this showing the counter experienced a race condition because it isn't the value 0 that it should be.

```console
Final Value = -1

Process finished with exit code 0
```

## Part 2: The Synchronized class

In this section, you will use the Java `sychronized` keyword to implement a mutex on the block of code that is responsible for accessing the value of the counter.

Replace the `Counter` class with this `SychronizedCounter` class. 
- Note that the only change is the addition of the synchronization block.
- The code for this is in the `SynchCounter` file in the solutions folder.
- 
```java
public class SyncCounter implements Runnable {
	  private int myCount = 0;

	  public void increment() {
	    try {
	      Thread.sleep(10);
	    } catch (Exception e) {
	      System.err.println(e);
	    }
	    this.myCount++;
	  }

	  public void decrement() {    
	    this.myCount--;
	  }

	  public int getValue() {
	    return this.myCount;
	  }
	    
		@Override
		public void run() {
			synchronized (this) {
				this.increment();
				System.out.println(
						"Value for Thread After increment " + Thread.currentThread().getName() 
						+ " " + this.getValue());
				this.decrement();
				System.out
						.println("Final value for Thread " + Thread.currentThread().getName() 
								+ " " + this.getValue());
			}
		}

}

```
Now replace the code for the new `Runner` class from the `Runner2` file in the Solutions folder. Don't forget to rename the class `Runner` instead of `Runner2'


```java
public class Runner {
	public static void main(String[] args) {
		SyncCounter counter = new SyncCounter();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(counter, "Thread " + i);
			t.start();

		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.err.println(e);
		}
		System.out.println("Final Value = " + counter.getValue());
	}

}
```

Run the code and notice that the race condition no longer occurs.
- Specifically, the counter is only taking in on the values of 0 and 1, which is what should be happening.

```console
Value for Thread After increment Thread 0 1
Final value for Thread Thread 0 0
Value for Thread After increment Thread 9 1
Final value for Thread Thread 9 0
Value for Thread After increment Thread 8 1
Final value for Thread Thread 8 0
Value for Thread After increment Thread 7 1
Final value for Thread Thread 7 0
Value for Thread After increment Thread 6 1
Final value for Thread Thread 6 0
Value for Thread After increment Thread 5 1
Final value for Thread Thread 5 0
Value for Thread After increment Thread 4 1
Final value for Thread Thread 4 0
Value for Thread After increment Thread 3 1
Final value for Thread Thread 3 0
Value for Thread After increment Thread 2 1
Final value for Thread Thread 2 0
Value for Thread After increment Thread 1 1
Final value for Thread Thread 1 0
Final Value = 0

Process finished with exit code 0
```

## Part 3: Wrong Synchronization

We can apply the `synchronize` keyword to the methods so that the only one thread at time can access the method
- The code is in the `Wrong` file in the Solutions folder
- The problem is that the race condition involves the interaction between two methods.
- Just having one of the methods synchronized means it is still possible for one method to increment while the other is decrementing
- In the code below, only the decrement is synchronized.

```java
class SyncCounter implements Runnable {
    private int myCount = 0;

    public  void increment() {
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            System.err.println(e);
        }
        this.myCount++;
    }

    public  synchronized void decrement() {
        this.myCount--;
    }

    public int getValue() {
        return this.myCount;
    }

    @Override
    public void run() {

            this.increment();
            System.out.println(
                    "Value for Thread After increment " + Thread.currentThread().getName()
                            + " " + this.getValue());
            this.decrement();
            System.out
                    .println("Final value for Thread " + Thread.currentThread().getName()
                            + " " + this.getValue());

    }

}

```

- You may need to run a lot of threads to see race condition. 
- In the `Runner` class, we increase the number of threads to 1000 and the final delay to 10,000  milliseconds.

```java 
public class Runner {
    public static void main(String[] args) {
        SyncCounter counter = new SyncCounter();
        for (int i = 0; i < 1000 ; i++) {
            Thread t = new Thread(counter, "Thread " + i);
            t.start();

        }
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("Final Value = " + counter.getValue());
    }

}
```

And the output looks like:

```console
Final value for Thread Thread 996 -2
Value for Thread After increment Thread 997 -1
Final value for Thread Thread 997 -2
Value for Thread After increment Thread 998 -1
Final value for Thread Thread 998 -2
Value for Thread After increment Thread 999 -1
Final value for Thread Thread 999 -2
Final Value = -2

Process finished with exit code 0
```

- Add the `synchronized` keyword to `increment` method and rerun.
- Can you explain the results?
- Notice the order of execution is different.
- When we synced the block, each thread did and a decrement and an increment, so the value of the counter never got higher than 1. 
- In this case, all the increments are done first, then all the decrements are done.
- The value of the counter takes on the range of values from 0 to the number of threads.
- This sort of behavior may produce some unexpected side effects.
- Synchronizing both methods in single block produces the behavior we actually want,

```java
class SyncCounter implements Runnable {
    private int myCount = 0;

    public synchronized void increment() {
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            System.err.println(e);
        }
        this.myCount++;
    }

    public  synchronized void decrement() {
        this.myCount--;
    }

```
```console
Value for Thread After increment Thread 0 1
Value for Thread After increment Thread 9 2
Value for Thread After increment Thread 8 3
Value for Thread After increment Thread 7 4
Value for Thread After increment Thread 6 5
Value for Thread After increment Thread 5 6
Value for Thread After increment Thread 4 7
Value for Thread After increment Thread 3 8
Value for Thread After increment Thread 2 9
Value for Thread After increment Thread 1 10
Final value for Thread Thread 1 4
Final value for Thread Thread 9 1
Final value for Thread Thread 0 0
Final value for Thread Thread 4 7
Final value for Thread Thread 7 3
Final value for Thread Thread 2 9
Final value for Thread Thread 6 5
Final value for Thread Thread 3 8
Final value for Thread Thread 5 6
Final value for Thread Thread 8 2
Final Value = 0

Process finished with exit code 0
```


## End Lab



