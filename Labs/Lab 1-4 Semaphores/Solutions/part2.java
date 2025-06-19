import java.util.concurrent.Semaphore;

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