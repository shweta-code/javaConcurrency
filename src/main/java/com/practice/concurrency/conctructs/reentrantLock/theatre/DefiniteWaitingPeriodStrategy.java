package com.practice.concurrency.reentrantLock.theatre;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
*
* If seat is booked , the max that I can do is to wait for some time for someone to cancel his/her booking.
*
* */
//TODO - Don't expose lock in strategies. Encapsulate these methods
public class DefiniteWaitingPeriodStrategy implements BookingStrategy {


    private final List<Theatre.Seat> seats;

    public DefiniteWaitingPeriodStrategy(List<Theatre.Seat> seats) {
        this.seats = seats;
    }
    /* @javadocs
     * <p>Acquires the lock if it is not held by another thread and
     * returns immediately with the value {@code true}, setting the
     * lock hold count to one. Even when this lock has been set to use a
     * fair ordering policy, a call to {@code tryLock()} <em>will</em>
     * immediately acquire the lock if it is available, whether or not
     * other threads are currently waiting for the lock.
     * This &quot;barging&quot; behavior can be useful in certain
     * circumstances, even though it breaks fairness. If you want to honor
     * the fairness setting for this lock, then use
     * {@link #tryLock(long, TimeUnit) tryLock(0, TimeUnit.SECONDS) }
     * which is almost equivalent (it also detects interruption).
     *
     * */

    //This method if you want the user to wait for 3 seconds
    public Boolean bookSeat(int i) throws InterruptedException {
        Theatre.Seat seat = seats.get(i);
        try {
            if(seat.getLock().tryLock(10 , TimeUnit.SECONDS)) {
                if (!seat.isBooked()) {
                    seat.setBooked(Boolean.TRUE);
                    return Boolean.TRUE;
                } else {
                    //seat.waitForSeat();
                      boolean waiting = true;
                      while(seat.isBooked() && waiting){
                          waiting = seat.await();
                      }

                      if(!seat.isBooked()){
                          seat.setBooked(Boolean.TRUE);
                          return Boolean.TRUE;
                      } else {
                          return Boolean.FALSE;
                      }

                }
            }
        } finally {
            seat.getLock().unlock();
        }
        return Boolean.FALSE;
    }

    @Override
    public int usersWaitingForSeat(int i){
        Theatre.Seat seat = seats.get(i);
        seat.getLock().lock();
        try {
            return seat.getCountOfUsersWaiting(i);
        } finally {
            seat.getLock().unlock();
        }
    }

    @Override
    public boolean cancel(int i)  {
        Theatre.Seat seat = seats.get(i);
        try {
            seat.getLock().lock();
            seat.setBooked(Boolean.FALSE);
            seat.notifyWaitingUsers();
        } finally {
            seat.getLock().unlock();
            return Boolean.TRUE;
        }

    }
}
