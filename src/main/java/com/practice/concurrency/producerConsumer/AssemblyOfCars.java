package com.practice.concurrency.producerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AssemblyOfCars {


    private  final int CAPACITY;

    public AssemblyOfCars(int CAPACITY) {
        this.CAPACITY = CAPACITY;
        cars = new ArrayBlockingQueue<String>(CAPACITY);
    }

    private BlockingQueue<String> cars;


    private void stepOne(String car){
        cars.add(car);
    }

    public static void main(String[] args) {
        AssemblyOfCars assemblyOfCars = new AssemblyOfCars(20);
        assemblyOfCars.start();

    }

    private void start(){
        new Thread(new StepOneWorker()).start();
        new Thread(new StepTwoWorker()).start();
    }

    private String  stepTwo(){
        try {
            return cars.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Producer
    class StepOneWorker implements Runnable {
        public void run() {
            for (int i = 0; i < CAPACITY; i++) {
                String name = "Car" + i;
                stepOne(name);
                System.out.println(name + " done with step One" );
            }

        }
    }



    //Consumer
    class StepTwoWorker implements Runnable {

        public void run() {
            for (int i = 0; i < CAPACITY; i++) {
                String carConsumed = stepTwo();
                System.out.println(carConsumed + " done with step two");
            }

        }
    }
}
