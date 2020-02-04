package com.practice.concurrency.collections;


/*
 * @miniTest
 *  What is delay queue?
 *  Implement Examination centre where few children go to give two exams. Every child has his own speed of giving exam. but no child
 *  can come before 3 seconds in a 15 seconds exam.
 *  After completion of one exam, kids will go to another room. --Cyclic Barrier
 *  They will enter examination hall on first come first basis.
 * */


/**
 * This is an unbounded BlockingQueue of objects that implement the Delayed
 * interface
 *
 * - DelayQueue keeps the elements internally until a certain delay has expired
 *
 * - an object can only be taken from the queue when its delay has expired !!! -
 *
 * We cannot place null items in the queue - The queue is sorted so that the
 * object at the head has a delay that has expired for the longest time.
 *
 * If no delay has expired, then there is no head element and poll( ) will
 * return null
 *
 * size() return the count of both expired and unexpired items !!!
 *
 */

public class DelayQueue {


}
