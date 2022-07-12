package com.ryana.programs;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class SynchronousOddEvenPrinter {

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    private static final Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {

        final var executors = Executors.newFixedThreadPool(2);
        executors.submit(SynchronousOddEvenPrinter::even);
        executors.submit(SynchronousOddEvenPrinter::odd);
        executors.shutdown();

    }

    private static void odd() {
        Thread.currentThread().setName(RED);
        IntStream.rangeClosed(0, 100)
                .filter(i -> i % 2 != 0)
                .forEach(SynchronousOddEvenPrinter::printer);
    }

    private static void even() {
        Thread.currentThread().setName(GREEN);
        IntStream.rangeClosed(0, 100)
                .filter(i -> i % 2 == 0)
                .forEach(SynchronousOddEvenPrinter::printer);
    }

    private static void printer(final int i) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + i);
            System.out.println(RESET);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
