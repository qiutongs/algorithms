package com.qiutongs.datastructure.array;

public interface Array<I> {
    public void add(I item);
    
    public void add(int index, I item);

    public I get(int index);

    public void set(int index, I value);

    public int search(I item);

    public int size();
}
