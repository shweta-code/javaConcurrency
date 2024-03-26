package com.practice2.concurrency;

import java.util.concurrent.atomic.AtomicBoolean;

// This is not working, Look at ping pong class.
// Sam functionality
public class PrintOneAndZeroAlternately {


    public static void main(String[] args) {
        Object lock = new Object();
        AtomicBoolean inT1 = new AtomicBoolean(false);

        Thread thread1 = new Thread(() -> {
            while(true){

                synchronized (lock){
                    System.out.println("Printing 1");
                    // inT1.set(true);

                    inT1.set(false);
                    lock.notify();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while(true){
                synchronized (lock){
                    while(inT1.get()){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Printing 2");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
