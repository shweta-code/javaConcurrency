package com.practice.concurrency;



/*
*
* @link
* https://javarevisited.blogspot.com/2012/07/cyclicbarrier-example-java-5-concurrency-tutorial.html
*
* @miniTest
* 1) Implement an event of 2 races with 10 racers in each race . Second race will happen when all racers in race one have crossed the finished line.
* 2) We will max wait for specified time to finish the race. then we can start the second race.
*
*
* */

/**
 *
 * Latch --> multiple threads can wait for each other
 *
 * A CyclicBarrier is used in situations where you want to create a group of
 * tasks to perform work in parallel + wait until they are all finished before
 * moving on to the next step -> something like join() -> something like
 * CountDownLatch
 *
 * CountDownLatch: one-shot event CyclicBarrier: it can be reused over and over
 * again
 *
 * + cyclicBarrier has a barrier action: a runnable, that will run automatically
 * when the count reaches 0 !!
 *
 * new CyclicBarrier(N) -> N threads will wait for each other
 *
 * WE CAN NOT REUSE LATCHES BUT WE CAN REUSE CyclicBarriers --> reset() !!!
 *
 */
public class CyclicBarrier {
}
