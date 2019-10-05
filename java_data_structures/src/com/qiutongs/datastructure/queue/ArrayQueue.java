package com.qiutongs.datastructure.queue;

import com.qiutongs.datastructure.array.DynamicArray;

public class ArrayQueue<I> implements Queue<I> {
    private final DynamicArray<I> array;
    private int head;

    public ArrayQueue() {
        this.array = new DynamicArray<>();
        this.head = 0;
    }

    @Override
    public void enqueue(I item) {
        array.add(item);
    }

    @Override
    public I dequeue() {
        if (size() == 0) {
            return null;
        }

        I result = array.get(head);
        head++;

        if (size() < array.size() / 2) {
            for (int i = head; i < array.size(); i++) {
                array.set(i - head, array.get(i));
            }

            for (int j = 1; j <= head; j++) {
                array.remove(array.size() - 1);
            }
            head = 0;
        }
        return result;
    }

    @Override
    public I front() {
        return size() == 0 ? null : array.get(head);
    }

    public int size() {
        return array.size() - head;
    }

    @Override
    public String toString() {
        return array.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        System.out.println("-------- Basic enqueue dequeue front --------  ");

        queue.enqueue(1);
        System.out.println("After enqueue 1: " + queue);
        System.out.println("Front: " + queue.front());

        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println("After enqueue 2,3,4: " + queue);
        System.out.println("Front: " + queue.front());

        System.out.println(queue);
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println(queue);
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println(queue);
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println(queue);
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println(queue);
        System.out.println("Dequeue: " + queue.dequeue());
    }
}
