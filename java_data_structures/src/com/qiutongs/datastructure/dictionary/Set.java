package com.qiutongs.datastructure.dictionary;

public interface Set<I extends Comparable<I>> {
    void add(I item);

    boolean remove(I item);

    boolean search(I item);
}
