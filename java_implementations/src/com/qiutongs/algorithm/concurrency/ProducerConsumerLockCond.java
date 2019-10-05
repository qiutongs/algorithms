package com.qiutongs.algorithm.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerLockCond extends ProducerConsumer {
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    @Override
    public void add(Object item) throws InterruptedException {
	lock.lock();
	while (size == MAX_SIZE) {
	    notFull.await();
	}

	addItem(item);

	notEmpty.signal();

	lock.unlock();
    }

    @Override
    public Object get() throws InterruptedException {
	lock.lock();
	while (size == 0) {
	    notEmpty.await();
	}

	Object result = getItem();

	notFull.signal();

	lock.unlock();

	return result;
    }
}
