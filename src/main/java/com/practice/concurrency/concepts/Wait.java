package com.practice.concurrency.concepts;

/*
* What does waiting the thread mean?
*
* @link
* https://dzone.com/articles/difference-between-blocked-waiting-timed-waiting-e
* */
public class Wait {
    public static void main(String[] args) {
        System.out.println("It means pausing the execution of thread");

        System.out.println("Wait will release all the acquired monitors");

        System.out.println("The important difference between the blocked and wait states is the impact on the scheduler. A thread in a blocked state is contending for a lock; that thread still counts as something the scheduler needs to service, possibly getting factored into the scheduler's decisions about how much time to give running threads (so that it can give the threads blocking on the lock a chance).\n" +
                "\n" +
                "Once a thread is in the wait state the stress it puts on the system is minimized, and the scheduler doesn't have to worry about it. It goes dormant until it receives a notification. Except for the fact that it keeps an OS thread occupied it is entirely out of play.\n" +
                "\n" +
                "This is why using notifyAll is less than ideal, it causes a bunch of threads that were previously happily dormant putting no load on the system to get woken up, where most of them will block until they can acquire the lock, find the condition they are waiting for is not true, and go back to waiting. It would be preferable to notify only those threads that have a chance of making progress.\n" +
                "\n" +
                "(Using ReentrantLock instead of intrinsic locks allows you to have multiple conditions for one lock, so that you can make sure the notified thread is one that's waiting on a particular condition, avoiding the lost-notification bug in the case of a thread getting notified for something it can't act on.)");
    }

}
