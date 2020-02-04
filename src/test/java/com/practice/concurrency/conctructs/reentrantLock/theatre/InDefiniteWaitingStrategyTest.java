package com.practice.concurrency.conctructs.reentrantLock.theatre;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

public class InDefiniteWaitingStrategyTest {

    @Test
    public void onlyOneUserIsAbleToBookASeat() throws InterruptedException {
        Theatre theatre = new Theatre(5 , Strategy.INDEFINITE_WAIT);

        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Boolean> submit = service.submit(() -> theatre.bookSeat(0));
        Future<Boolean> submit2 = service.submit(() -> theatre.bookSeat(0));
        service.shutdown();

        service.awaitTermination(1, TimeUnit.DAYS);
        try {
            Assert.assertEquals(Boolean.TRUE , submit.get());
            Assert.assertEquals(Boolean.TRUE , submit.get() ^ submit2.get());
            Assert.assertEquals(Boolean.FALSE ,  submit2.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }



    @Test
    public void cancelTheSeat() throws InterruptedException, ExecutionException {

        Theatre theatre = new Theatre(5 , Strategy.INDEFINITE_WAIT);
        ExecutorService service = Executors.newFixedThreadPool(30);
        Future<Boolean> submit = service.submit(() -> theatre.bookSeat(0));
        Thread.sleep(1000);
        Future<Boolean> submit1 = service.submit(() -> theatre.bookSeat(0));
        Thread.sleep(2000);
        for (int i = 0; i < 20; i++) {
            service.submit(() -> theatre.bookSeat(0));
        }
        Thread.sleep(3000);
        Future<Boolean> cancel = service.submit(() -> theatre.cancel(0));
        Thread.sleep(10);


        service.shutdown();

        service.awaitTermination(1, TimeUnit.DAYS);
        Assert.assertEquals(Boolean.TRUE , cancel.get());
        Assert.assertEquals(Boolean.TRUE , submit.get());
        Assert.assertEquals(Boolean.TRUE , submit1.get());


    }


    @Test
    public void firstUserGetsTheSeat() throws InterruptedException {
        Theatre theatre = new Theatre(5 , Strategy.INDEFINITE_WAIT);

        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Boolean> submit = service.submit(() -> theatre.bookSeat(0));
        Thread.sleep(2000);
        Future<Boolean> submit2 = service.submit(() -> theatre.bookSeat(0));
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
        try {
            Assert.assertEquals(Boolean.TRUE , submit.get());
            Assert.assertEquals(Boolean.FALSE , submit2.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
