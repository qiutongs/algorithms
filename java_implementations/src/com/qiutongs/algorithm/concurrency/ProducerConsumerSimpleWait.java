package com.qiutongs.algorithm.concurrency;

public class ProducerConsumerSimpleWait extends ProducerConsumer {

    public synchronized void add(Object item) throws InterruptedException {
	while (size == MAX_SIZE) {
	    wait();
	}

	addItem(item);

	notify();
    }

    public synchronized Object get() throws InterruptedException {
	while (size == 0) {
	    wait();
	}

	Object result = getItem();

	notify();

	return result;
    }
}
