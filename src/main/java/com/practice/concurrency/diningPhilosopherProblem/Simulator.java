package com.practice.concurrency.diningPhilosopherProblem;


import java.util.ArrayList;
import java.util.List;

/*

@link
https://www.baeldung.com/java-dining-philoshophers
* The diagram above represents the problem. There are five silent philosophers (P1 – P5)
* sitting around a circular table, spending their lives eating and thinking.

There are five forks for them to share (1 – 5) and to be able to eat, a philosopher needs to
have forks in both his hands. After eating, he puts both of them down
and then they can be picked by another philosopher who repeats the same cycle.

The goal is to come up with a scheme/protocol that helps the philosophers achieve their goal of
 eating and thinking without getting starved to death.
*
*
*
* Nuances of the problem
* 1) Deadlock : If every ph ilosopher picks up the left chopstick. and keeps waiting for the right indefinitely. Deadlock will occur.
* 2) Starvation :
* */
public class Simulator {

    public static void main(String[] args) throws InterruptedException {

        List<ChopStick> chopSticks = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            chopSticks.add(new ChopStick(i));
        }
        for (int i = 0; i < 5; i++) {

            Philosopher philosopher = new Philosopher(i ,chopSticks.get((6+i)%5) ,chopSticks.get(i));
            Thread thread = new Thread(philosopher);
            thread.start();

        }

    }
}
