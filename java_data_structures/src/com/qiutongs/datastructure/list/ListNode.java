package com.qiutongs.datastructure.list;

public class ListNode<I> {
    public ListNode<I> next;
    public I item;

    public void print() {
	StringBuilder sb = new StringBuilder();

	ListNode<I> curNode = this;

	while (curNode != null) {
	    sb.append(curNode + " -> ");
	    curNode = curNode.next;
	}

	sb.append("null");

	System.out.println(sb.toString());
    }

    @Override
    public String toString() {
	return item.toString();
    }
}
