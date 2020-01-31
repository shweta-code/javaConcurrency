package com.practice.concurrency.producerConsumer.lowLevelConcurrency;

import java.util.LinkedList;
import java.util.Queue;

public class LowLevelAssemblyOfCars {

    private Queue<String> cars ;
    private final int CAPACITY;

    public LowLevelAssemblyOfCars(int CAPACITY) {
        this.CAPACITY = CAPACITY;
        cars = new LinkedList<>();
    }


    private void stepOne(){

        //process
        int count = 0;
        while(CAPACITY != cars.size()) {
            String name = "CAR" + count;
            synchronized (cars) {
                cars.add(name);
                System.out.printf("Processing %s in step one" ,name);
                System.out.println();
                try {
                    Thread.sleep(500);
                    count++;
                    cars.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(CAPACITY == cars.size()){
                    try {
                        cars.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }

    private String  stepTWo(){
        String name;
        synchronized (cars) {
            if (0 == cars.size()) {
                try {
                    cars.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            name = cars.remove();
            System.out.printf("Processing %s car in step two" , name);
            System.out.println();
            cars.notify();
        }


        return name;
    }

    private void start(){

        Thread stepOneWorker = new Thread(new Runnable() {
            @Override
            public void run() {
               stepOne();
            }
        });

        Thread stepTwoWorker = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    stepTWo();
                }

            }
        });

        stepOneWorker.start();
        stepTwoWorker.start();

        try {
            stepOneWorker.join();
            stepTwoWorker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    public static void main(String[] args) {

        LowLevelAssemblyOfCars assemblyOfCars = new LowLevelAssemblyOfCars(5);
        assemblyOfCars.start();

    }
}
