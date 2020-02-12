package com.practice.concurrency.diningPhilosopherProblem;

import java.util.Random;

public class Philosopher implements Runnable {

    private ChopStick left;
    private ChopStick right;
    private int id;

    public Philosopher(int i, ChopStick left, ChopStick right) {
        id = i;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {

        while (true) {
            try {
                thinking();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean leftAcquired;
            boolean rightAcquired;
            leftAcquired = pickChopStick(left);
            rightAcquired = pickChopStick(right);
            try {
                if(leftAcquired && rightAcquired) {
                    eat();
                }
                if(leftAcquired){
                    left.release();
                }
                if(rightAcquired){
                    right.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void eat() throws InterruptedException {
        System.out.printf("Philosopher %s eating.." , id);
        System.out.println();
        Random random = new Random();
        Thread.sleep(random.nextInt(4000));
    }

    private boolean pickChopStick(ChopStick chopStick) {
        try {
            return chopStick.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        return false;
    }


    private void thinking() throws InterruptedException {
        System.out.printf("Philosopher %s thinking.." , id);
        System.out.println();
        Random random = new Random();
        Thread.sleep(random.nextInt(4000));
    }
}
