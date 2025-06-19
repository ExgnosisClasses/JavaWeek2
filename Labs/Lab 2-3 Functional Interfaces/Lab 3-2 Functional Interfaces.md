# Lab 2-3 Functional Interfaces

## Lab Objectives

This lab is designed to help you understand functional interfaces using threads as an example

## Part 1: The Functional Interface

A functional interface is one that has only one abstract method.
- Create a standard main class, and then add the following interface definition after the main class
- This lab assumes you are using the package `funct3`
- The code is in `part1.java` in the solutions folder

```java
package funct3;

public class Main {

    public static void main(String[] args) {

    }
}

@FunctionalInterface
interface Func {
    void exec();
    // note that adding the following method produces a compile error since
    // we no longer have exactly one abstract method
    // void show();
}
```

- Uncomment out the `void show()` method to confirm that a functional interface only allows one abstract method

## Part 2: The Old Thread Model

Start with the same thread model introduced in the last module.
- The code is in `part2.java` in the Solutions folder.

```java
package funct3;

public class Main {

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread());
        t1.start();
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("MyThread " + Thread.currentThread() + "count=" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

```

Your output should look something like this.


```console
MyThread Thread[#35,Thread-0,5,main]count=1
MyThread Thread[#35,Thread-0,5,main]count=2
MyThread Thread[#35,Thread-0,5,main]count=3
MyThread Thread[#35,Thread-0,5,main]count=4
MyThread Thread[#35,Thread-0,5,main]count=5

Process finished with exit code 0
```

## Part 3: Using a Runnable variable

Since the only role of the `MyThread` class is to hold the `run()` method,we can use a variable or type `Runnable`, assign the function body to it as a Lamba and then pass the variable right to the Thread class.

- In your code, delete the `MyThread` class and create the function variable as shown.
- Lambda functions are considered to implement the `Runnable` interface so the code is considered to implicitly override the `run()` function.
- The code for this section is in `part3.java`

```java
ackage funct3;

public class Main {

    public static void main(String[] args) {

        Runnable r = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("MyThread " + Thread.currentThread() + "count=" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(r);

        t1.start();
    }
}
```

- You should see the same output as before

We can also just pass the Lamba function directly to the Thread object. 
- Normally, when we do this, the functions are a lot smaller, so the resulting code is easier to read.
- The resulting code looks like this.
- This is in `part4.java` in the Solutions folder.

```java
ackage funct3;

public class Main {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("MyThread "  + Thread.currentThread() + "count=" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();

    }
}

```

Run the code and you should see the same output as before


This exercise shows the trend in Java to offload the boilerplate code to the java environment rather than having the programmer write it.
- Creating your own thread objects to just hold the `run()` is no longer considered best practice in Java.
- This was the standard way of doing it until Java 8 introduced functional programming capabilities.
- Instead, you should use Lamba's directly whereever possible as long as it doesn't make the code difficult to read.


## End Lab