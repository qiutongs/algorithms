package com.qiutongs.algorithm.concurrency;

import java.util.Random;

public class ProducerConsumerTest {

    public static void main(String[] args) throws InterruptedException {
	ProducerConsumer pcList = new ProducerConsumerSimpleWait();

	Thread producer = new Thread(() -> {
	    Random rand = new Random();

	    while (true) {
		try {
		    int num = rand.nextInt();

		    pcList.add(num);
		    System.out.println("Produced " + num);

		    Thread.sleep(2000);
		} catch (InterruptedException e) {
		    System.out.println("Producer is interrupted");
		    break;
		}
	    }
	});

	Thread consumer = new Thread(() -> {
	    while (true) {
		try {
		    int num = (int) pcList.get();
		    System.out.println("Consumed " + num);
		} catch (InterruptedException e) {
		    System.out.println("Consumer is interrupted");
		    break;
		}
	    }
	});

	producer.start();
	consumer.start();

	Thread.sleep(5000);
	
	producer.interrupt();
	consumer.interrupt();
	
	producer.join();
	consumer.join();
	
	System.out.println("Simulation ends");
    }
}
