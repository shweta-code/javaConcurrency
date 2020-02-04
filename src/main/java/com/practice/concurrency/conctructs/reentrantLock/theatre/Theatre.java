package com.practice.concurrency.conctructs.reentrantLock.theatre;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/*
* *  Design a Theatre
 *  Booking Seat features:
 *  1) User will try to book limited amount of seats.--
 *  2) If a user cancels his seat, the longest waiting user should get the preference.--
 *  3) If longest waiting user also cancels the seat. The next longest waiting user should get the seat then.--
 *  4) I should always be able to retrieve no of users who are waiting.--
 *  5) In case of contention , user should be immediately initimated if there are no seats and he has been passed to waiting queue.--
 *  6) Ability to wait for definite period of time.--
 *  For cancellation ,w e have assumed only the user who books the seat shall be able to cancel. So there cannot be multiple threads for cancelling the seats.
 **/

//TODO - Build a vip user facility for all strategies so that common user's reservation is cancelled and vip user's reservation is made.
//Hint : creating just a method would do, no need of special data structure,
public class Theatre {

    private List<Seat> seats;
    private BookingStrategy strategy;

    class Seat {

        private boolean isBooked = false;
        private ReentrantLock lock;
        private Condition condition;

        public Seat(int number) {
            //Fairness parameter is set to true so that user who comes first get the seat.
            lock = new ReentrantLock(true);
            condition = lock.newCondition();
        }

        public ReentrantLock getLock() {
            return lock;
        }

        public boolean isBooked() {
            return isBooked;
        }

        public void setBooked(boolean booked) {
            isBooked = booked;
        }

        public boolean await() throws InterruptedException {
            boolean await = condition.await(9, TimeUnit.SECONDS);
            //System.out.println(await);
            return await;
        }

        public int getCountOfUsersWaiting(int seatNo){
            //users who are blocked for this lock, who are trying to acquire
            int blockedUsers = lock.getQueueLength();

            //Users who are waiting for this seat.
            int waitQueueLength = lock.getWaitQueueLength(condition);

            return blockedUsers + waitQueueLength;


        }

        public void notifyWaitingUsers(){
            condition.signalAll();
        }


    }
    public Theatre(int CAPACITY, Strategy strategy) {
        seats = new ArrayList<>(CAPACITY);
        for (int i = 0; i < CAPACITY; i++) {
            seats.add(new Seat(i));
        }
        this.strategy = StrategyFactory.getStrategy(strategy,seats);
    }


    public boolean bookSeat(int seatNo) throws InterruptedException {
        return strategy.bookSeat(seatNo);
    }

    public boolean cancel(int seatNo) throws InterruptedException {
        return strategy.cancel(seatNo);
    }

    public int usersWaitingForSeat(int seatNo){
        return strategy.usersWaitingForSeat(seatNo);
    }


}


