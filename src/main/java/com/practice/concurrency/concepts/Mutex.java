package com.practice.concurrency;


import java.util.ArrayList;
import java.util.List;

/*
* @miniTest
* 1) What is mutex?
* 2) Illustrate with example
*
* */
public class Mutex {
    public static void main(String[] args) {
        System.out.println("Mutex mean to allow multiple threads access to a section but not simultaneously");
        System.out.println("give serialized access to a code section or resource");
        System.out.println("So generally a class and object can serve as mutex");
        System.out.println("Because they have built in monitors" +
                "that can serve as mutexes" +
                "");
    }
}

class MutexExampleOne {

    private List<Integer> list = new ArrayList<Integer>();
    private Object lock = new Object();

    private  void increment(){
        synchronized (lock){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10000; i++) {
                list.add(i);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        final MutexExampleOne exampleOne = new MutexExampleOne();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    exampleOne.increment();
                }
            });
            thread.start();
            /*try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/


        }
         Thread.sleep(5000);
        System.out.println(exampleOne.list.size());
    }


}
