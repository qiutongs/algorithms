package com.qiutongs.algorithm.concurrency;

import java.util.concurrent.Semaphore;

public class ProducerConsumerSemaphore extends ProducerConsumer {

    private final Semaphore lock = new Semaphore(1);
    private final Semaphore emptyCount = new Semaphore(MAX_SIZE);
    private final Semaphore fullCount = new Semaphore(0);

    @Override
    public void add(Object item) throws InterruptedException {
	emptyCount.acquire();
	lock.acquire();

	addItem(item);

	lock.release();
	fullCount.release();
    }

    @Override
    public Object get() throws InterruptedException {
	fullCount.acquire();
	lock.acquire();

	Object result = getItem();

	lock.release();
	emptyCount.release();

	return result;
    }

}
