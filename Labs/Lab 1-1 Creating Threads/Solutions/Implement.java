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
