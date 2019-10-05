package com.qiutongs.algorithm.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReadWriteLock implements ReadWriteLock {

    private Lock mutex = new ReentrantLock();
    private Condition readCondition = mutex.newCondition();
    private Condition writeCondition = mutex.newCondition();

    private ReadLock readLock = new ReadLock();
    private ReadLock writeLock = new ReadLock();

    private int counter = 0;

    @Override
    public Lock readLock() {
	return readLock;
    }

    @Override
    public Lock writeLock() {
	return writeLock;
    }

    class ReadLock implements Lock {

	@Override
	public void lock() {
	    mutex.lock();

	    while (counter == -1) {
		try {
		    readCondition.await();
		} catch (InterruptedException e) {
		    // Swallow the interrupted exception.
		}
	    }

	    counter++;
	    mutex.unlock();
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
	    throw new UnsupportedOperationException();
	}

	@Override
	public boolean tryLock() {
	    throw new UnsupportedOperationException();
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
	    throw new UnsupportedOperationException();
	}

	@Override
	public void unlock() {
	    mutex.lock();

	    counter--;

	    if (counter == 0) {
		writeCondition.signal();
	    }

	    mutex.unlock();
	}

	@Override
	public Condition newCondition() {
	    throw new UnsupportedOperationException();
	}

    }

    class WriteLock implements Lock {

	@Override
	public void lock() {
	    mutex.lock();

	    while (counter != 0) {
		try {
		    writeCondition.await();
		} catch (InterruptedException e) {
		    // Swallow the interrupted exception.
		}
	    }

	    counter = -1;
	    mutex.unlock();
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
	    throw new UnsupportedOperationException();
	}

	@Override
	public boolean tryLock() {
	    throw new UnsupportedOperationException();
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
	    throw new UnsupportedOperationException();
	}

	@Override
	public void unlock() {
	    mutex.lock();

	    counter = 0;
	    readCondition.signalAll();
	    writeCondition.signal();

	    mutex.unlock();
	}

	@Override
	public Condition newCondition() {
	    throw new UnsupportedOperationException();
	}

    }

}
