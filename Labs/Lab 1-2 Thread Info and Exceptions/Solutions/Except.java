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