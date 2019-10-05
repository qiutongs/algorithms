package com.qiutongs.datastructure.tree;

public class MinSegmentTree<I extends Comparable<I>> extends SegmentTree<I> {

    public MinSegmentTree(I[] items) {
	super(items);
    }

    @Override
    protected I merge(I left, I right) {
	return left.compareTo(right) < 0 ? left : right;
    }

    @Override
    protected I update(I curNodeValue, I oldItem, I newItem) {
	if (newItem.compareTo(curNodeValue) < 0) {
	    return newItem;
	} else {
	    return curNodeValue;
	}
    }

    public static void main(String[] args) {
	Integer items[] = { 1, 2, 3, 4, 5 };

	MinSegmentTree<Integer> sTree = new MinSegmentTree<>(items);
	System.out.println(sTree);

	System.out.println(sTree.query(1, 3));
	sTree.update(0, 0);
	System.out.println(sTree.query(0, 4));
    }
}
