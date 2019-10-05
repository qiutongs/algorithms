package com.qiutongs.datastructure.dictionary;

import com.qiutongs.datastructure.array.SortedDynamicArray;

public class SortedArraySet<I extends Comparable<I>> extends ArraySet<I> {

    public SortedArraySet() {
        super(new SortedDynamicArray<I>());
    }

    public static void main(String[] args) {
        SortedArraySet<Integer> set = new SortedArraySet<>();

        set.add(1);
        set.add(2);

        System.out.println("Searching existed number: " + set.search(1));
        System.out.println("Searching not existed number: " + set.search(3));

        System.out.println("Remove existed number: " + set.remove(1));
        System.out.println("Searching again: " + set.search(1));
    }
}
