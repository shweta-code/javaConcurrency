package com.practice.concurrency.countDownLatch;


//*
// @miniTest
// 1) What is countdown latch?
// 2) Imagine one application :
//   * Imagine restaurant's manager is wanting to close restaurant after five last customers finish their food.
//   * Imagine bride is waiting to enter the hall when five important members are in the hall. The members are coming and going out of the hall. She will start walking the moment
//   everyone is in. -- Not a valid example of CountDownLatch
//   * Program I will leave the house only if Aahaan has woken up , I will wait for Lalita to come else, I will wait for Abhi to get up.
//
//
//  @link
//    https://javarevisited.blogspot.com/2012/07/countdownlatch-example-in-java.html
// */

public class CountDownLatch {

    public static void main(String[] args) {
        System.out.println("Countdown latch is construct that allows a thread to wait until other some fixed no of threads do not finish their tasks");
    }
}
