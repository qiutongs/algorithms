package com.qiutongs.algorithm.concurrency;

public abstract class ProducerConsumer {

    protected static int MAX_SIZE = 5;

    protected final Object[] items = new Object[MAX_SIZE];
    protected int size = 0;

    public abstract void add(Object item) throws InterruptedException;

    public abstract Object get() throws InterruptedException;
    
    protected void addItem(Object item) {
	items[size] = item;
	size++;
    }
    
    protected Object getItem() {
	Object result = items[size - 1];
	items[size - 1] = null;
	size--;
	return result;
    }
}
