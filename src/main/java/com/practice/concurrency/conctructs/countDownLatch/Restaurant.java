package com.practice.concurrency.conctructs.countDownLatch;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/*
*
* * Imagine restaurant's manager is wanting to close restaurant after five last customers finish their food.
*
*
* This example is not a valid  countdown example.
* So basically any time there are less than 5 customers countdownlatch will decrement . New customer may enter even after that . Customer set size can increment
* after decrementing for some time. There is no provision to update count of countdownlatch. It is not a valid
* construct for this example. I think semaphore will work here.
*
* */
public class Restaurant {

    Manager manager;
    Set<Customer> customerSet = new HashSet<Customer>(7);
    CountDownLatch countDownLatch = new CountDownLatch(5);
    class Manager extends Thread {

        public void run() {
            System.out.println("Opening Restaurant");
            try {
                countDownLatch.await();
                System.out.println("Closing Restaurant");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Customer {

    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }



    public void newCustomer(Customer customer){
        System.out.println("New customer entered");
        customerSet.add(customer);
    }

    public void leaveCustomer(Customer customer){

        customerSet.remove(customer);
        if(  customerSet.size() <= 5 && customerSet.size() > 0) {
            System.out.println("Last 5 customers leaving");
            countDownLatch.countDown();
        }

    }


    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        restaurant.process();
    }


    public  void process() {

        Manager manager = new Manager();

        this.setManager(manager);
        manager.start();

        Customer customer_1 = new Customer();
        Customer customer_2 = new Customer();
        Customer customer_3 = new Customer();
        Customer customer_4 = new Customer();
        Customer customer_5 = new Customer();
        Customer customer_6 = new Customer();
        this.newCustomer(customer_1);
        this.newCustomer(customer_2);
        this.newCustomer(customer_3);
        this.newCustomer(customer_4);
        this.newCustomer(customer_5);
        this.newCustomer(customer_6);
        this.leaveCustomer(customer_1);
        this.leaveCustomer(customer_2);
        this.leaveCustomer(customer_3);
        this.leaveCustomer(customer_4);
        this.leaveCustomer(customer_5);
        this.leaveCustomer(customer_6);
        this.leaveCustomer(customer_6);

    }
}
