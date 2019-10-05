package com.qiutongs.algorithm.concurrency;

public class ProducerConsumerSingleMutex extends ProducerConsumer {

    @Override
    public synchronized void add(Object item) throws InterruptedException {
	while (true) {
	    if (size < MAX_SIZE) {
		addItem(item);
	    }
	    Thread.sleep(500);
	}
    }

    @Override
    public synchronized Object get() throws InterruptedException {
	while (true) {
	    if (size > 0) {
		return getItem();
	    }
	    Thread.sleep(500);
	}
    }

}
