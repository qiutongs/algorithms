package com.qiutongs.datastructure.stack;

import com.qiutongs.datastructure.list.DoubleLinkedList;

public class LinkedListStack<I> implements Stack<I>{

    private final DoubleLinkedList<I> list;
    
    public LinkedListStack() {
        this.list = new DoubleLinkedList<>();
    }
    
    @Override
    public void push(I item) {
        list.append(item);
    }

    @Override
    public I top() {
        return list.tail();
    }

    @Override
    public I pop() {
        return list.removeTail();
    }
    
    @Override
    public String toString() {
        return list.toString();
    }
    
    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();

        System.out.println("-------- Basic push, top, pop --------  ");

        stack.push(1);
        System.out.println("After pushing 1: " + stack);
        System.out.println("Top: " + stack.top());


        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("After pusing 2,3,4: " + stack);
        System.out.println("Top: " + stack.top());
        
        System.out.println("Pop: " +  stack.pop());
        System.out.println("Top: " + stack.top());
        System.out.println("Pop: " +  stack.pop());
        System.out.println("Top: " + stack.top());
        System.out.println("Pop: " +  stack.pop());
        System.out.println("Top: " + stack.top());
        System.out.println("Pop: " +  stack.pop());
        System.out.println("Top: " + stack.top());
    }
}
