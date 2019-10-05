package com.qiutongs.datastructure.dictionary;

import com.qiutongs.datastructure.array.DynamicArray;

public abstract class ArraySet<I extends Comparable<I>> implements Set<I> {
    private final DynamicArray<I> array;

    public ArraySet(DynamicArray<I> array) {
        this.array = array;
    }

    @Override
    public void add(I item) {
        // Only add if item is not present in the array
        if (array.search(item) < 0) {
            array.add(item);
        }
    }

    @Override
    public boolean remove(I item) {
        int index = array.search(item);

        // If found, remove the item by its index
        if (index >= 0) {
            array.remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean search(I item) {
        return array.search(item) >= 0;
    }
}
