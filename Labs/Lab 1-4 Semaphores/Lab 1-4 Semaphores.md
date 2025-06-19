# Lab 1-4 Semaphores

## Lab Objectives

In this lab, we are going to use semaphores to control access to a printer resource which can be used by two threads at the same time.

## Part 1: Create the Printer Class

- Create the printer class complete with semaphore 
- When the print method is called by a thread
  - It tries to get a permit
  - If it does, it prints and releases the permit
  - if not, it blocks until a permit is available
- Note: if the permits are not released, the system will lock up with all the threads blocked and waiting for permits that will never be available. 


```java
import java.util.concurrent.Semaphore;

class Printer {
    // Allow only 2 threads to access the printer at the same time
    private final Semaphore semaphore = new Semaphore(2);

    public void print(String jobName) {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting to print: " + jobName);
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " is printing: " + jobName);
            Thread.sleep(2000); // Simulate printing time
            System.out.println(Thread.currentThread().getName() + " finished printing: " + jobName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(); // Release the permit
        }
    }
}
```

Now create a `PrintJob` class


```java
class PrintJob implements Runnable {
    private final Printer printer;
    private final String jobName;

    public PrintJob(Printer printer, String jobName) {
        this.printer = printer;
        this.jobName = jobName;
    }

    @Override
    public void run() {
        printer.print(jobName);
    }
}

```

Now create the `Runner` class to create the Printer and the PrintJobs


```java
public class Runner {
    public static void main(String[] args) {
        Printer sharedPrinter = new Printer();

        // Create 5 users trying to print
        Thread job1 = new Thread(new PrintJob(sharedPrinter, "Document-1"), "PrinterJob 1");
        Thread job2 = new Thread(new PrintJob(sharedPrinter, "Document-2"), "PrinterJob 2");
        Thread job3 = new Thread(new PrintJob(sharedPrinter, "Document-3"), "PrinterJob 3");
        Thread job4 = new Thread(new PrintJob(sharedPrinter, "Document-4"), "PrinterJob 4");
        Thread job5 = new Thread(new PrintJob(sharedPrinter, "Document-5"), "PrinterJob 5");

        // Start all print jobs
        job1.start();
        job2.start();
        job3.start();
        job4.start();
        job5.start();
    }
}
```

Your output should look something like this

```console
PrinterJob 4 is waiting to print: Document-4
PrinterJob 2 is waiting to print: Document-2
PrinterJob 1 is waiting to print: Document-1
PrinterJob 3 is waiting to print: Document-3
UPrinterJob 5 is waiting to print: Document-5
PrinterJob 2 is printing: Document-2
PrinterJob 4 is printing: Document-4
PrinterJob 4 finished printing: Document-4
PrinterJob 2 finished printing: Document-2
PrinterJob 1 is printing: Document-1
UPrinterJob 5 is printing: Document-5
PrinterJob 1 finished printing: Document-1
UPrinterJob 5 finished printing: Document-5
PrinterJob 3 is printing: Document-3
PrinterJob 3 finished printing: Document-3

Process finished with exit code 0
```

The code is in `part1.java` in the solutions folder

## Part 2: Non blocking semaphore

Sometimes we want the thread to not block but to continue on with some other task while trying periodically checking for a permit.

In this variant, we use the `tryAquire()` method that returns a true if a permit is acquired and false otherwise

The ony change we have to make is in the Printer class

```java
class Printer {
    private final Semaphore semaphore = new Semaphore(2); // only 2 users can print at once

    public void print(String jobName) {
        System.out.println(Thread.currentThread().getName() + " is attempting to print: " + jobName);

        if (semaphore.tryAcquire()) {
            try {
                System.out.println(Thread.currentThread().getName() + " is printing: " + jobName);
                Thread.sleep(2000); // Simulate printing
                System.out.println(Thread.currentThread().getName() + " finished printing: " + jobName);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " could NOT print: " + jobName + " — Printer busy");
        }
    }
}

```

The change in output shows that instead of blocking the thread that can't get a permit executes other code, in this case just printing out that it could not print.

```java
class Printer {
    private final Semaphore semaphore = new Semaphore(2); // only 2 users can print at once

    public void print(String jobName) {
        System.out.println(Thread.currentThread().getName() + " is attempting to print: " + jobName);

        if (semaphore.tryAcquire()) {
            try {
                System.out.println(Thread.currentThread().getName() + " is printing: " + jobName);
                Thread.sleep(2000); // Simulate printing
                System.out.println(Thread.currentThread().getName() + " finished printing: " + jobName);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " could NOT print: " + jobName + " — Printer busy");
        }
    }
}

```

- This code is available in `part2.java` in the Solutions folder.

Now the output looks like this

```console
PrinterJob 4 is attempting to print: Document-4
PrinterJob 5 is attempting to print: Document-5
PrinterJob 4 is printing: Document-4
PrinterJob 1 is attempting to print: Document-1
PrinterJob 3 is attempting to print: Document-3
PrinterJob 2 is attempting to print: Document-2
PrinterJob 5 is printing: Document-5
PrinterJob 1 could NOT print: Document-1 — Printer busy
PrinterJob 3 could NOT print: Document-3 — Printer busy
PrinterJob 2 could NOT print: Document-2 — Printer busy
PrinterJob 4 finished printing: Document-4
PrinterJob 5 finished printing: Document-5

Process finished with exit code 0
```

## End Lab