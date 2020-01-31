package com.practice.concurrency;


import java.util.ArrayList;
import java.util.List;

/*
*
* @miniTest
* Why do we need volatile?
* Illustrate with example
* What is the guarantee that volatile gives?
* What volatile fails to give?
* When should we use volatile?
*
* @link
* Can you make array or arrayList or a collection for that matter volatile?
* https://javarevisited.blogspot.com/2017/01/can-we-make-array-volatile-in-java.html
*
* */
public class Volatile {


    public static void main(String[] args) {

        System.out.println("Volatile gives read safety");
        System.out.println("If two or more threads modifies a variable , it may so happen that the variable does not end up having expected value.");
        System.out.println("Volatile keyword is meant for variables only.");
        System.out.println("It is not meant for methods and code blocks");

    }




}


/*
*
* It is not a good example of volatile variable.
* This code will break.
* One suprising addition is when you uncomment sout in this program.
*
*
* @link
* https://stackoverflow.com/questions/38527429/what-are-the-possible-problems-caused-by-adding-elements-to-unsynchronized-array
* https://stackoverflow.com/questions/5554734/what-causes-a-java-lang-arrayindexoutofboundsexception-and-how-do-i-prevent-it
*
*
* */
class ExampleOne {


    static volatile List<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    list.add(i);
                    //System.out.println(Thread.currentThread().getName() + "==== "+list.size());
                }
            }
        };

        Runnable r2 = new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    list.add(i);
                    //System.out.println(Thread.currentThread().getName() + "==== "+list.size());
                }
            }
        };

        Thread thread1 = new Thread(r1);
        thread1.setName("Shweta");
        thread1.start();
        Thread thread2 = new Thread(r2);
        thread2.setName("Abhinav");
        thread2.start();



        try {
            thread2.join();
            thread1.join();
        } catch (InterruptedException e) {

        }

        System.out.println(list.size());

    }

}



/*
* Volatility does not guarantee atomicity.
*
*
* @miniTest
* 1) What is atomicity?
*
*
* */
class ExampleTwo {

    private int counter = 0;
    private  volatile int volatileCOunter = 0;

    public static void main(String[] args) {
        ExampleTwo exampleTwo = new ExampleTwo();
        exampleTwo.initiateThreads();
        System.out.println(exampleTwo.counter);
        System.out.println(exampleTwo.volatileCOunter);

    }

    public void initiateThreads( ){
        Runnable r1 = new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    counter++;
                    volatileCOunter++;
                }
            }
        };

        Runnable r2 = new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    counter++;
                    volatileCOunter++;
                }
            }
        };

        Thread thread1 = new Thread(r1);
        thread1.setName("Shweta");
        thread1.start();
        Thread thread2 = new Thread(r2);
        thread2.setName("Abhinav");
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {

        }



    }

}


/*
*
* I dont know why this is not working.
* It is supposed to work.
*
*
* @link
* https://javarevisited.blogspot.com/2011/06/volatile-keyword-java-example-tutorial.html
* */
class ExampleThree {

    //private boolean keepRunning = true;
    private volatile boolean keepRunning = true;


    public static void main(String[] args) {

        ExampleThree exampleThree = new ExampleThree();
        exampleThree.initiateThreadOne();
        exampleThree.initiateThreadTwo();

    }

    private void initiateThreadOne() {

        Runnable r1 = new Runnable() {
            public void run() {
                while (keepRunning){
                    System.out.println("Hello");
                }
            }
        };


        Thread thread1 = new Thread(r1);
        thread1.setName("Shweta");
        thread1.start();



        try {
            thread1.join();

        } catch (InterruptedException e) {

        }

    }


    private void initiateThreadTwo() {



        Runnable r2 = new Runnable() {
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                keepRunning = false;
            }
        };


        Thread thread2 = new Thread(r2);
        thread2.setName("Abhinav");
        thread2.start();


        try {

            thread2.join();
        } catch (InterruptedException e) {

        }

    }
}
