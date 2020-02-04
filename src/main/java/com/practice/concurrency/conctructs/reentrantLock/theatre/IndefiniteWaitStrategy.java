package com.practice.concurrency.conctructs.reentrantLock.theatre;

import java.util.List;


//TODO - this code is wrong. While booking seat we are trying to block threads, rather we should wait for indefinite time use condition.await()
//while cacelling I should awaken all waiting threads to acquire seat fairly
//TODO - test cases
public class IndefiniteWaitStrategy implements BookingStrategy {


    private final List<Theatre.Seat> seats;

    public IndefiniteWaitStrategy(List<Theatre.Seat> seats) {
        this.seats = seats;
    }

    //If you want the user to wait
    public Boolean bookSeat(int i) throws InterruptedException {
        Theatre.Seat seat = seats.get(i);
        try {
            seat.getLock().lock();
            if (!seat.isBooked()) {
                seat.setBooked(Boolean.TRUE);
                return Boolean.TRUE;
            } else {
                seat.setBooked(Boolean.FALSE);
                return Boolean.FALSE;
            }
        } finally {
            seat.getLock().unlock();
        }
    }


    @Override
    public int usersWaitingForSeat(int i){
        Theatre.Seat seat = seats.get(i);
        return seat.getLock().getQueueLength();
    }

    @Override
    public boolean cancel(int i)  {
        Theatre.Seat seat = seats.get(i);
        try {
            seat.getLock().lockInterruptibly();
            seat.setBooked(Boolean.FALSE);
        } finally {
            seat.getLock().unlock();
            return Boolean.TRUE;
        }


    }
}
