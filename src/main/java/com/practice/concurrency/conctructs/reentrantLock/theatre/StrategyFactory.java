package com.practice.concurrency.conctructs.reentrantLock.theatre;

import java.util.List;

public abstract class StrategyFactory {

    public static BookingStrategy getStrategy(Strategy strategy,List<Theatre.Seat> seats){

        if(Strategy.DEFINITE_WAIT == strategy){
            return  new DefiniteWaitingPeriodStrategy(seats);
        } else if (Strategy.NO_WAIT == strategy){
            return new NoWaitStrategy(seats);
        } else {
            return new IndefiniteWaitStrategy(seats);
        }

    }
}
