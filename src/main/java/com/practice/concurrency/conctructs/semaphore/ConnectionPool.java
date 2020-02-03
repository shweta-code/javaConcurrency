package com.practice.concurrency.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {

    private final int NO_OF_CONNECTIONS;
    private java.util.concurrent.Semaphore lock;

    public ConnectionPool(int noOfConnections) {
        this.NO_OF_CONNECTIONS = noOfConnections;
        lock = new Semaphore(NO_OF_CONNECTIONS);
    }

    public void connect() throws InterruptedException {
        lock.acquire();
        //process
        System.out.printf("Active connections are %s", NO_OF_CONNECTIONS - lock.availablePermits());
        System.out.println();
        lock.release();
    }
}


class App{

    public static void main(String[] args) throws InterruptedException {
        final ConnectionPool connectionPool = new ConnectionPool(10);
        ExecutorService service = Executors.newFixedThreadPool(25);

        for (int i = 0; i < 30; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        connectionPool.connect();
                    } catch (InterruptedException e) {


                    }
                }
            });
        }


        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
    }

}
