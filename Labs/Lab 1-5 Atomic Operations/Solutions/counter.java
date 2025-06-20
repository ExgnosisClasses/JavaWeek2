class Counter implements Runnable {
    private static final AtomicLong count = new AtomicLong(0);

    public long increment() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.err.println(e);
        }

        return Counter.count.incrementAndGet();
    }

    public long decrement() {
        return Counter.count.decrementAndGet();
    }

    public long getCount() {
        return Counter.count.get();
    }

    @Override
    public void run() {
        this.increment();
        this.decrement();

    }
}