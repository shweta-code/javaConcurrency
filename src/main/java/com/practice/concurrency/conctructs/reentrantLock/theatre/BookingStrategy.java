package com.practice.concurrency.conctructs.reentrantLock.theatre;

public interface BookingStrategy {

    Boolean bookSeat(int i) throws InterruptedException;
    int usersWaitingForSeat(int i);
    boolean cancel(int i) throws InterruptedException;
}
