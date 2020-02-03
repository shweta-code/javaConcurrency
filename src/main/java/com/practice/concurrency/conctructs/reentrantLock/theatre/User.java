package com.practice.concurrency.reentrantLock.theatre;

import java.util.concurrent.Callable;

public class User implements Callable<Boolean> {


    private  Theatre theatre;
    private  int seatNo;


    public User(Theatre theatre, int seatNo) {
        this.theatre = theatre;
        this.seatNo = seatNo;
    }

    /*private void bookSeatForATheatre(){
        try {
            if(theatre.bookSeat(seatNo)) {
                System.out.printf("Seat %s booked successfully", seatNo);
            } else {
                System.out.printf("Seat %s already occupied" , seatNo);
            }

            System.out.println();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //what should happen if this user is interrupted while acuring the lock for this seat
        }
    }*/


    public boolean cancel() throws InterruptedException {
        return theatre.cancel(seatNo);
    }


    @Override
    public Boolean call() throws Exception {
        return theatre.bookSeat(seatNo);
    }


}
