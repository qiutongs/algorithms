package com.qiutongs.datastructure.list;

public class SingleLinkedList<I> implements List<I> {

    private ListNode<I> head;
    private ListNode<I> tail;

    public SingleLinkedList() {
	head = tail = null;
    }

    @Override
    public void append(I item) {
	ListNode<I> n = new ListNode<>();
	n.item = item;

	if (tail == null) {
	    head = tail = n;
	    n.next = null;
	} else {
	    tail.next = n;
	    n.next = null;
	    tail = n;
	}
    }

    @Override
    public void insertHead(I item) {
	// TODO Auto-generated method stub
    }

    @Override
    public I search(I item) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public I removeTail() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();

	ListNode<I> curNode = head;

	while (curNode != null) {
	    sb.append(curNode + " -> ");
	    curNode = curNode.next;
	}

	sb.append("null");

	return sb.toString();
    }

    public ListNode<I> getHead() {
	return head;
    }
    
    public void reverse() {
	ListNode<I> curNode = head, preNode = null;

	while (curNode != null) {
	    ListNode<I> tmpNext = curNode.next;
	    curNode.next = preNode;

	    preNode = curNode;
	    curNode = tmpNext;
	}

	head = preNode;
    }

    public static void main(String[] args) {
	SingleLinkedList<Integer> list = new SingleLinkedList<>();

	list.append(1);
	list.append(2);
	list.append(3);

	System.out.println(list);

	list.reverse();
	System.out.println(list);
    }

}
