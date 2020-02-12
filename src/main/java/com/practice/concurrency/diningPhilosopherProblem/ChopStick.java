package com.practice.concurrency.diningPhilosopherProblem;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {

    private int id;
    private ReentrantLock lock;

    public ChopStick(int id) {
        this.id = id;
        lock = new ReentrantLock();
    }

    public boolean acquire() throws InterruptedException {
        return lock.tryLock(3, TimeUnit.SECONDS);
    }

    public void release(){
        lock.unlock();
    }
}

