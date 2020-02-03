package com.practice.concurrency.reentrantLock;


/*
*
* @miniTest
*
* What are the diasdvantages of Reentrant Lock?
*
*  @link
*  https://javarevisited.blogspot.com/2013/03/reentrantlock-example-in-java-synchronized-difference-vs-lock.html
*
*  3) One more worth noting difference between ReentrantLock and synchronized keyword in Java is, ability to interrupt Thread while waiting for Lock. In case of synchronized keyword, a thread can be blocked waiting for lock, for an indefinite period of time and there was no way to control that. ReentrantLock provides a method called lockInterruptibly(), which can be used to interrupt thread when it is waiting for lock. Similarly tryLock() with timeout can be used to timeout if lock is not available in certain time period.

Read more: https://javarevisited.blogspot.com/2013/03/reentrantlock-example-in-java-synchronized-difference-vs-lock.html#ixzz6BvN9An1D
*
* */
public class ReentrantLock {

    public static void main(String[] args) {

        //Disadvantages
        System.out.println("Lot of try and finally blocks.");
    }
}
