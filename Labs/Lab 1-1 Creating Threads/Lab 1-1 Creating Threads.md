# Lab 1-1 Creating Threads


---

## Lab Overview

The first part of this lab creates a thread-runnable object by extending the `Thread` class. 
- This specific method of creating runnable objects is a construct found in older Java code before constructs like `Executor` interfaces became a more common way of managing runnable objects.
- If you are writing new Java code, you should avoid this approach since it is less efficient and requires you to write a lot of boilerplate code. 
- However, if you are working supporting legacy Java installations, you may find this pattern of creating runnable objects in the code.


## Part 1: Creating the Task Class

The purpose of the `Task` class is to override the `run()` method in the Java `Thread` class.  
- The `Thread` class has all the code necessary to create and schedule an object of type `Task` so that you don't have to. 
- For example, the `start()` method in the `Thread` creates the runnable object and schedules it for execution by the thread manager in the JVM

The only thing the `Task` class _must_ do is override the `run()` method with the code that should execute as a thread. 

The `Task` object takes a `String` variable as a name so that we see can tell what thread is running.

The `Thread.sleep()` method yields the CPU for a given amount of time but still might have to deal with an `interrupt` thrown as an `InterruptedException` while it is asleep.

An `interrupt` is a message sent to a thread that it should stop whatever it is doing and do something else, like stop listening on an input port.

```java
class Task extends Thread {
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

The code for this section is in the file `Extends.java` in teh Solutions folder



## Part 2: Create the Runner class.

- Create two `Task` objects and give them unique names. 
- Send each a `start()` method. 
- Note that this method is defined in the `Thread` class.

```java
public class Runner {

	public static void main(String[] args) {
		Task t1 = new Task("one");
		Task t2 = new Task("two");
		t1.start();
		t2.start();
	}
}
```

- Since the two objects are running concurrently, their output statements will be interleaved but not exactly in the order you see here

```console
Running one 1
Running one 2
Running one 3
Running one 4
Running one 5
Running two 1
Running one 6
Running two 2
Running one 7
Running one 8
Running one 9
Running two 3
Running one 10
Running two 4
Running two 5
Running two 6
Running two 7
Running two 8
Running two 9
Running two 10
```

Run the code several times
- Notice that the order the threads print out varies. 
- This is called _nondeterminism_ which means that we can't predict when the threads start exactly when each will get to run. 
- This is because other factors like background JVM threads, like the garbage collector, also will take turns using CPU.

## Part 3: Running the code as a non-thread

The `start()` method sets the `Task` class to run as a thread and automatically calls the `run()` method. 
- If we call the `run()` method directly instead, the code is not run as a thread
- It's just run as an ordinary sequence of execution.
- THe first `Task` object runs to completion, followed by the second `Task` object.

Instead of calling the `start()` method, call the `run()` on both `Task` objects.

```java
	public static void main(String[] args) {
		Task t1 = new Task("one");
		Task t2 = new Task("two");
		//t1.start();
		//t2.start();
		t1.run();
		t2.run();
	}
```
```console
Running one 1
Running one 2
Running one 3
Running one 4
Running one 5
Running one 6
Running one 7
Running one 8
Running one 9
Running one 10
Running two 1
Running two 2
Running two 3
Running two 4
Running two 5
Running two 6
Running two 7
Running two 8
Running two 9
Running two 10
```

## Part 4: The Runnable Interface

This part modifies the previous result to use the `Runnable` interface rather than inheritance. 
- This is actually a more efficient method since there is not the overhead maintaining an inheritance hierarchy.

All we need to do to create a thread is give the `Thread` class any object that implements the `run()` method. In later labs, we will get away from having to create objects to hold the `run()` method.

###Creating the Task Class

The only change we make in the `Task` class is to chance from inheritance to implementation. Notice that we are still overriding the `run()` method in both cases.

```java
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



##  Creating the Runner Class

We have a `Runnable` object use dependency injection to wrap it in a `Thread` object. Everything works exactly as before.

```java
public class Runner {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Task("one"));
		Thread t2 = new Thread(new Task("two"));
		t1.start();
		t2.start();
	}

}
```
```console
Running one 1
Running one 2
Running two 1
Running two 2
Running one 3
Running two 3
Running one 4
Running one 5
Running two 4
Running two 5
Running two 6
Running two 7
Running two 8
Running two 9
Running one 6
Running one 7
Running one 8
Running one 9
Running one 10
Running two 10

```



---
## End Lab



