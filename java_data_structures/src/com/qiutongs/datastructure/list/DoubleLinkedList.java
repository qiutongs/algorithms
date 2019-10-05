package com.qiutongs.datastructure.list;

public class DoubleLinkedList<I> implements List<I> {

    private Node<I> head;
    private Node<I> tail;

    public DoubleLinkedList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void append(I item) {
        Node<I> n = new Node<>(item);

        if (tail == null) {
            head = tail = n;
            n.previous = null;
            n.next = null;
        } else {
            tail.next = n;
            n.previous = tail;
            n.next = null;
            tail = n;
        }
    }

    public void insertHead(I item) {
        Node<I> n = new Node<>(item);

        if (head == null) {
            head = tail = n;
            n.previous = null;
            n.next = null;
        } else {
            n.next = head;
            n.previous = null;
            head.previous = n;
            head = n;
        }
    }

    public I search(I item) {
        Node<I> curNode = head;

        while (curNode != null) {
            if (item.equals(curNode.item)) {
                return curNode.item;
            }
            curNode = curNode.next;
        }

        return null;
    }

    public I removeTail() {
        if (tail == null) {
            return null;
        }

        Node<I> currentTail = tail;

        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.previous;
        }

        return currentTail.item;
    }

    public I head() {
        return head == null ? null : head.item;
    }

    public I tail() {
        return tail == null ? null : tail.item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node<I> curNode = head;

        while (curNode != null) {
            sb.append(curNode + " -> ");
            curNode = curNode.next;
        }

        sb.append("null");

        return sb.toString();
    }

    static class Node<I> {
        private Node<I> previous;
        private Node<I> next;
        private I item;

        public Node(I item) {
            this.previous = null;
            this.next = null;
            this.item = item;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

        System.out.println("-------- Basic append, get, set, search --------  ");

        list.append(1);
        System.out.println("After appending 1: " + list);
        System.out.println("Search existed element: " + list.search(1));
        System.out.println("Search not existed element: " + list.search(2));

        list.append(2);
        list.append(3);
        list.append(4);
        System.out.println("After appending 2,3,4: " + list);

        list.insertHead(5);
        list.insertHead(6);
        list.insertHead(7);
        System.out.println("After inserting head 5,6,7: " + list);

        System.out.println("Search head: " + list.search(7));
        System.out.println("Search tail: " + list.search(4));
    }
}
