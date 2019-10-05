package com.qiutongs.datastructure.stack;

public interface Stack<I> {
    void push(I item);

    I top();

    I pop();
}
