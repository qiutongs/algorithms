package com.qiutongs.datastructure.stack;

import com.qiutongs.datastructure.array.DynamicArray;

public class ArrayStack<I> implements Stack<I> {

    private final DynamicArray<I> array;

    public ArrayStack() {
        this.array = new DynamicArray<>();
    }

    @Override
    public void push(I item) {
        array.add(item);
    }

    @Override
    public I top() {
        return array.size() == 0 ? null : array.get(array.size() - 1);
    }

    @Override
    public I pop() {
        return array.remove(array.size() - 1);
    }

    @Override
    public String toString() {
        return array.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        System.out.println("-------- Basic push, top, pop --------  ");

        stack.push(1);
        System.out.println("After pushing 1: " + stack);
        System.out.println("Top: " + stack.top());

        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("After pusing 2,3,4: " + stack);
        System.out.println("Top: " + stack.top());

        System.out.println("Pop: " + stack.pop());
        System.out.println("Top: " + stack.top());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Top: " + stack.top());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Top: " + stack.top());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Top: " + stack.top());
    }

}
