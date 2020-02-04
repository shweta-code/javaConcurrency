package com.practice.concurrency.conctructs.countDownLatch;


/*
*Enhance this program to include Aahaan who will notify when he wakes up.
* */
public class Home {
    java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(1);

    class CareTaker implements Runnable{

        private void enterHouse(){
            System.out.println("Lalita entered into house");
            countDownLatch.countDown();
        }

        public void run() {
            startForMyHome();
            enterHouse();
            takeCareOfAahaan();
        }

        private void startForMyHome() {
            System.out.println("Lalita starts for home");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void takeCareOfAahaan() {
            System.out.println("Taking care of Aahaan");
        }
    }



    class Abhinav implements Runnable{

        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gettingUp();
            gotUp();
        }

        private void gotUp() {
            System.out.println("Abhinav Got up");
            countDownLatch.countDown();
        }

        private void gettingUp() {
            System.out.println("Abhinav is Getting up");
        }
    }


    class Me implements Runnable{
        public void run(){
            doMyWorkInMorning();
            try {
                leave();
            } catch (InterruptedException e) {

            }
            doMyWorkInOffice();
        }

        private void doMyWorkInMorning() {
            System.out.println("Doing my work in morning");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doMyWorkInOffice() {
            System.out.println("Doing my work in office");
        }

        private void leave() throws InterruptedException {

            countDownLatch.await();
            System.out.println("Me leaving for office");
        }
    }

    public void process() {
        Me me = new Me();
        CareTaker careTaker = new CareTaker();
        Abhinav abhinav = new Abhinav();
        new Thread(me).start();
        new Thread(careTaker).start();
        new Thread(abhinav).start();
    }
    public static void main(String[] args) {
        Home home = new Home();
        home.process();


    }
}
