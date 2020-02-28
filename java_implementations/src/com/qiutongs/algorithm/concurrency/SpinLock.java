package com.qiutongs.algorithm.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SpinLock implements Lock {

    AtomicBoolean locked = new AtomicBoolean();
    
    @Override
    public void lock() {
	while(locked.compareAndSet(false, true) == false) {
	    
	}
    }

    @Override
    public void unlock() {
	locked.set(false);
    }
    
    @Override
    public void lockInterruptibly() throws InterruptedException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public boolean tryLock() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public Condition newCondition() {
	// TODO Auto-generated method stub
	return null;
    }

}
