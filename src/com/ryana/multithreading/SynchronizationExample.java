package com.ryana.multithreading;

public class SynchronizationExample {

    private static int x;
    public static void main(String[] args) {
        final var t1 = new Thread(SynchronizationExample::increment);
        final var t2 = new Thread(SynchronizationExample::decrement);

        t1.start();
        t2.start();

        
        try {
            //TimeUnit.SECONDS.sleep(10);
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(x);      
    }

    private static void increment() {
        for (int i = 0; i < 10_000; i++) {
            x++;
        }
    }

    private static void decrement() {
        for (int i = 0; i < 10_000; i++) {
            x--;
        }
    }


}
