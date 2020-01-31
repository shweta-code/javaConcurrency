package com.practice.concurrency.reentrantLock.theatre;

public enum Strategy {

    NO_WAIT,
    INDEFINITE_WAIT,
    DEFINITE_WAIT;


    Strategy() {
    }
}
