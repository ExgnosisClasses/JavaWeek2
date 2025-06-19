
class Counter implements Runnable {
    private int myCount = 0;

    public void increment() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.err.println(e);
        }
        this.myCount++;
    }

    public void decrement() {
        this.myCount--;
    }

    public int getValue() {
        return this.myCount;
    }

    @Override
    public void run() {
        this.increment();
        this.decrement();
        // Uncomment out the line below if you want to see the value of the counter after each thread executes
       // System.out.println("Final value for Thread " + Thread.currentThread().getName() + " " + this.getValue());

    }
}
