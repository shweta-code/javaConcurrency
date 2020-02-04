package com.practice.concurrency.collections.exchanger.pointOfSale;

import java.util.concurrent.Exchanger;

public class PointOfSale implements Runnable{

    Exchangeable goods;
    Exchanger<Exchangeable> exchanger;

    public PointOfSale(Exchanger<Exchangeable> exchanger) {
        goods = new Goods();
        this.exchanger = exchanger;
    }

    public Exchangeable getGoods() {
        return goods;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("I am ready to sell goods");
            Thread.sleep(3000);
            goods = exchanger.exchange(goods);
            System.out.println("Hurray !! sold and got money");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

      Exchanger<Exchangeable> exchanger = new Exchanger<>();
        PointOfSale pointOfSale = new PointOfSale(exchanger);
        Thread t = new Thread(pointOfSale);
        Customer customer = new Customer(exchanger);
        Thread t2 = new Thread(customer);
        /*PointOfSale pointOfSale1 = new PointOfSale(exchanger);
        Thread t3 = new Thread(pointOfSale1);
        Customer customer1 = new Customer(exchanger);
        Thread t4 = new Thread(customer1);*/
      t.start();
      t2.start();
      //t3.start();
      //t4.start();



        try {
            t.join();
            t2.join();
            //t3.join();
            //t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Point of sale has %s", pointOfSale.getGoods().toString());
        System.out.println();
        System.out.printf("Customer has %s", customer.getMoney().toString());

    }
}

class Customer implements Runnable{

    Exchangeable money;
    Exchanger<Exchangeable> exchanger;

    public Customer(Exchanger<Exchangeable> exchanger) {
        money = new Money();
        this.exchanger = exchanger;
    }


    public Exchangeable getMoney() {
        return money;
    }

    @Override
    public void run() {
        try {
            System.out.println("I want to buy goods");
            money = exchanger.exchange(money);
            System.out.println("I purchased goods");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

