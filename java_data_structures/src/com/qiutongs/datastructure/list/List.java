package com.qiutongs.datastructure.list;

public interface List<I> {
    public void append(I item);

    public void insertHead(I item);

    public I search(I item);

    public I removeTail();
}
