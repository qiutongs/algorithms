package com.qiutongs.algorithm.sort;

import com.qiutongs.datastructure.list.ListNode;
import com.qiutongs.datastructure.list.SingleLinkedList;

public class MergeSort {

    public static ListNode<Integer> sort(ListNode<Integer> list) {
	if (list.next == null) {
	    return list;
	}

	ListNode<Integer> middleNode = split(list);
	return merge(sort(list), sort(middleNode));
    }

    private static ListNode<Integer> merge(ListNode<Integer> list1, ListNode<Integer> list2) {
	ListNode<Integer> iter1 = list1, iter2 = list2;
	ListNode<Integer> newHead = null;
	ListNode<Integer> tail = null;

	while (iter1 != null && iter2 != null) {
	    ListNode<Integer> smallerNode = null;

	    if (iter1.item < iter2.item) {
		smallerNode = iter1;
		iter1 = iter1.next;
	    } else {
		smallerNode = iter2;
		iter2 = iter2.next;
	    }

	    if (newHead == null) {
		newHead = smallerNode;
		tail = smallerNode;
	    } else {
		tail.next = smallerNode;
		tail = smallerNode;
	    }
	}

	while (iter1 != null) {
	    tail.next = iter1;
	    tail = iter1;
	    iter1 = iter1.next;
	}

	while (iter2 != null) {
	    tail.next = iter2;
	    tail = iter2;
	    iter2 = iter2.next;
	}

	tail.next = null;

	return newHead;
    }

    private static ListNode<Integer> split(ListNode<Integer> list) {
	ListNode<Integer> slow = list, fast = list;

	while (fast.next != null && fast.next.next != null) {
	    fast = fast.next.next;
	    slow = slow.next;
	}

	ListNode<Integer> result = slow.next;
	slow.next = null;
	return result;
    }

    public static void main(String[] args) {
	SingleLinkedList<Integer> list = new SingleLinkedList<>();

	list.append(4);
	list.append(5);
	list.append(1);
	list.append(9);
	list.append(8);

	list.getHead().print();
	ListNode<Integer> newHead = MergeSort.sort(list.getHead());
	newHead.print();
    }
}
