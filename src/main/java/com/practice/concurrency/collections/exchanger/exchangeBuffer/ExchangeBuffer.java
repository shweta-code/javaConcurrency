package com.practice.concurrency.collections.exchanger.exchangeBuffer;


import java.util.concurrent.Exchanger;

public class ExchangeBuffer {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        Thread t1 = new Thread(new StringBuffer("Aahaan" , 1, exchanger));
        Thread t2 = new Thread(new StringBuffer("Shweta" , 2 , exchanger));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



}


class StringBuffer implements Runnable{

    private String s;
    private int id;
    Exchanger<String> exchanger;

    public String getS() {
        return s;
    }

    public StringBuffer(String s, int id, Exchanger<String> exchanger) {
        this.s = s;
        this.id = id;
        this.exchanger = exchanger;
    }

    @Override
    public void run()
    {
        try {
            Thread.sleep(2000);
            System.out.printf("%s am ready to exchange %s ", id , s);
            System.out.println();
             s = exchanger.exchange(s);
            System.out.printf("%s have exchanged . updated value is  %s", id , s);
            System.out.println();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}