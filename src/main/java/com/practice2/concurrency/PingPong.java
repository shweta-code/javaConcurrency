package com.practice2.concurrency;

// If you dig deeper
// both the threads are inside synchronized block always
// what happens is when wait is called thread goes into waiting state and leave the monitor
// which is when pong gets to enter the block

// What happens when a thread can't acquire synchronized block lock?
// Does it keep waiting how long?
// Can a thread acquire synchronized block lock for same object again? Are synchronized blocks reentrant?
public class PingPong extends Thread {
    static StringBuilder object = new StringBuilder("");

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new PingPong();
        Thread t2 = new PingPong();

        t1.setName("\nping");
        t2.setName(" pong");

        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        working();
    }

    void working() {

        // In this case having synchronized and while(true) interchangably
        // is going to work
            synchronized (object) {
                while (true) {
                    try {
                        System.out.print(Thread.currentThread().getName());
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

    }
}
