package com.qiutongs.datastructure.heap;

import java.util.ArrayList;
import java.util.Arrays;

public class Heap<I extends Comparable<I>> {
    // last parent (internal node) = last leaf's parent = ((heap_size - 1) - 1 ) / 2
    // = heap_size/2 - 1
    // starting from heap_size/2, are the leaves
    private ArrayList<I> items;

    public Heap(I[] items) {
	this.items = new ArrayList<I>(Arrays.asList(items));

	for (int i = items.length - 1; i >= 0; i--) {
	    heapifyDown(i);
	}
    }

    public I getMin() {
	return items.isEmpty() ? null : items.get(0);
    }

    public void insert(I newItem) {
	items.add(newItem);
	heapifyUp(items.size() - 1);
    }

    public void deleteMin() {
	swap(0, items.size() - 1);
	items.remove(items.size() - 1);
	heapifyDown(0);
    }

    public void update(int i, I newItem) {
	I currentItem = items.get(i);
	items.set(i, newItem);

	if (newItem.compareTo(currentItem) < 0) {
	    heapifyUp(i);
	} else if (newItem.compareTo(currentItem) > 0) {
	    heapifyDown(i);
	}
    }

    private void heapifyUp(int p) {
	while (p > 0) {
	    int parent = (p - 1) / 2;

	    if (items.get(p).compareTo(items.get(parent)) < 0) {
		swap(p, parent);
		p = parent;
	    } else {
		break;
	    }
	}
    }

    private void heapifyDown(int p) {
	while (p < items.size() / 2) {
	    int minIndex = getMinIndex(p);

	    if (p == minIndex) {
		break;
	    }

	    swap(p, minIndex);
	    p = minIndex;
	}
    }

    @Override
    public String toString() {
	return items.toString();
    }

    private int getMinIndex(int parentIndex) {
	int left = parentIndex * 2 + 1;
	int right = parentIndex * 2 + 2;

	I min = items.get(parentIndex);
	int result = parentIndex;

	if (items.get(left).compareTo(min) < 0) {
	    min = items.get(left);
	    result = left;
	}

	if (right < items.size() && items.get(right).compareTo(min) < 0) {
	    min = items.get(right);
	    result = right;
	}

	return result;
    }

    private void swap(int index1, int index2) {
	I temp = items.get(index1);
	items.set(index1, items.get(index2));
	items.set(index2, temp);
    }

    public static void main(String[] args) {
	Integer numbers[] = { 113, 400, 818, 612, 411, 311, 412, 420 };
	Heap<Integer> heap = new Heap<>(numbers);
	System.out.println(heap);

	System.out.println("update a smaller value");
	heap.update(3, 10);
	System.out.println(heap);

	System.out.println("update a bigger value");
	heap.update(0, 1000);
	System.out.println(heap);

	System.out.println("insert a small value");
	heap.insert(66);
	System.out.println(heap);

	System.out.println("delete min");
	heap.deleteMin();
	System.out.println(heap);
    }
}
