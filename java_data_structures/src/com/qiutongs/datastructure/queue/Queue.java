package com.qiutongs.datastructure.queue;

public interface Queue<I> {
    void enqueue(I item);

    I dequeue();

    I front();
}
