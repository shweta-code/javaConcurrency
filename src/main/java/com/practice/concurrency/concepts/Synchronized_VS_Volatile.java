package com.practice.concurrency.concepts;


/*
* In Volatile.java
* Example One and two are examples of synchronized
*
*
*
* @link
* https://javarevisited.blogspot.com/2011/06/volatile-keyword-java-example-tutorial.html
* */
public class Synchronized_VS_Volatile {

    public static void main(String[] args) {
        System.out.println("Volatility guarantees visibility only");
        System.out.println("Volatility guarantees happen before. The value will be not read before writing is complete");

    }

}
