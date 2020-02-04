package com.practice.concurrency.conctructs.reentrantLock.theatre;

import java.util.List;
import java.util.concurrent.TimeUnit;


//TODO -test cases
public class NoWaitStrategy implements BookingStrategy {

    private final List<Theatre.Seat> seats;

    public NoWaitStrategy(List<Theatre.Seat> seats) {
        this.seats = seats;
    }

    /* Why haven't I ued tryLock() here?  */
    /* @javadocs
    *tryLock()
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
        if(seat.getLock().tryLock(0 , TimeUnit.SECONDS) && !seat.isBooked()) {
            try {
                seat.setBooked(true);
                return Boolean.TRUE;
            } finally {
                seat.getLock().unlock();
            }


        }
        return Boolean.FALSE;
    }

    @Override
    public int usersWaitingForSeat(int i) {
        return 0;
    }

    @Override
    public boolean cancel(int i) {
        Theatre.Seat seat = seats.get(i);
        seat.getLock().lock();
        seat.setBooked(Boolean.FALSE);
        seat.getLock().unlock();
        return Boolean.TRUE;
    }

}
