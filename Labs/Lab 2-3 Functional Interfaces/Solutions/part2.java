
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