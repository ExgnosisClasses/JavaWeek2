# Lab 1-2 Thread Info and Exceptions



## Lab Overview

In this lab, we explore some of the methods provided by the `Thread` class and see how exceptions affect the execution of threads


## Part 1: Creating a User Thread

- There are a number of JVM system threads, called _daemon_ threads that run in the background.
- The `main()` method and any thread we create are called _user_ threads.
- Create a user thread class that just goes to sleep when it starts up.

```java                                   
class myThread implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
```


## Part 2: Examining Thread Info

- The `main()` method creates a user thread, sets the name to `MyThread` and sets the thread priority to `7`.
- The for loop gets a list of all the threads running and displays information about each one.

```java 
import java.util.Set;

public class Runner {
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new myThread());
		t1.setName("MyThread");
		t1.setPriority(7);
		t1.start();
		
		Set<Thread> threads = Thread.getAllStackTraces().keySet();
		System.out.printf("%-15s \t %-15s \t %-15s \t %s\n", "Name", "State", "Priority", "isDaemon");
		for (Thread t : threads) {
		    System.out.printf("%-15s \t %-15s \t %-15d \t %s\n", t.getName(), t.getState(), t.getPriority(), t.isDaemon());
		}
	}
}
```

- You should see something like this:

```console

Name            	 State           	 Priority        	 isDaemon
Notification Thread  RUNNABLE        	 9               	 true
Reference Handler 	 RUNNABLE        	 10              	 true
Signal Dispatcher 	 RUNNABLE        	 9               	 true
main            	 RUNNABLE        	 5               	 false
Finalizer       	 WAITING         	 8               	 true
Monitor Ctrl-Break 	 RUNNABLE        	 5               	 true
MyThread        	 TIMED_WAITING   	 7               	 false
Common-Cleaner  	 TIMED_WAITING   	 8               	 true
```

The code for this section is in the file `info.java` in the Solutions folder.

## Part 3: Exceptions

- Use the code from Lab 1-1. If you don't have it, the starter code is here:

```java
public class Runner {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Task("one"));
		Thread t2 = new Thread(new Task("two"));
		t1.start();
		t2.start();
	}

}

class Task implements Runnable {
	private String name = null;
	
	public Task(String n) {
		this.name = n;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++){
			System.out.println("Running " + this.name + " " + i);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}
}
```

- Throwing an exception in a thread terminates that thread. 
- However, all the other threads that are executing will continue until they are finished. - This includes the `main()` thread, if it terminates due to an exception, any other threads it spawned will continue.

- Add the exception code as shown.

```java
public class Runner {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Task("one"));
		Thread t2 = new Thread(new Task("two"));
		t1.start();
		t2.start();
		// throw new RuntimeException();
	}

}

class Task implements Runnable {
	private String name = null;
	
	public Task(String n) {
		this.name = n;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++){
			System.out.println("Running " + this.name + " " + i);
			// if ((this.name == "one") && (i == 5)) throw new RuntimeException(); 
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}
}

```

- Run the code to see how it works without exceptions.
- Uncomment the exception in the `main()` method and note that the other threads keep on running.

- You should see something like the following
- There is a bit of a lag from when the exception is thrown to when the message is printed out, so it may appear in a different place in your output
- Note that the two spawned threads continue to run even when the main thread has stopped
- The main thread is not special, it is a peer thread with the other two that you spawned

```console
Exception in thread "main" java.lang.RuntimeException
	at Runner.main(Runner.java:8)
Running one 1
Running two 1
Running one 2
Running one 3
Running one 4
Running one 5
Running one 6
Running one 7
Running two 2
Running two 3
Running two 4
Running one 8
Running one 9
Running two 5
Running one 10
Running two 6
Running two 7
Running two 8
Running two 9
Running two 10

Process finished with exit code 1
```

- Replace the comment in the `main()` method
- Now comment out the exception code in the `run()` method and see what happens.
- You should see something like the following
- Note that the thread `one` stops executing when the count >5 
- As before, the printout doesn't happen immediately, so there is a lag from when the exception is thrown to when it is printed out
- The application returned a 1 when the exception was in the main thread, but 0 here.
- A return code of 0 indicates success, return code of 1 indicates failure
- The reason is that it is the `main()` method that is called by the JVM
  - In the first case, the exception was in `main()` so a 1 was returned
  - In the second case, `main()` finished successfully so it returned a 0 to the JVM

```console
Running two 1
Running one 1
Running one 2
Running one 3
Running one 4
Running one 5
Running two 2
Running two 3
Running two 4
Running two 5
Running two 6
Running two 7
Running two 8
Running two 9
Running two 10
Exception in thread "Thread-0" java.lang.RuntimeException
	at Task.run(Runner.java:24)
	at java.base/java.lang.Thread.run(Thread.java:1447)

Process finished with exit code 0
```

The code for this section is in the file `Except.java` in the solutions folder.


---

## End Lab



