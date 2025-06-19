package pools;

import java.util.concurrent.*;

public class Runner {

    public static void main(String[] args) {

        int corePoolSize = 3;
        int maxPoolSize = 10;
        long keepAliveTime = 3000;

        BlockingQueue<Runnable> pool = new ArrayBlockingQueue<Runnable>(13);
        ExecutorService myPool = new ThreadPoolExecutor(
                corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, pool);

        try {

            for (int i = 1; i < 20; i++) {
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