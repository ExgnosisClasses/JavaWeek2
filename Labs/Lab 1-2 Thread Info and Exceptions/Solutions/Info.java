
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