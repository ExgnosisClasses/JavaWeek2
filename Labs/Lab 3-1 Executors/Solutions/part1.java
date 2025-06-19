package pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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