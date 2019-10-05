package com.qiutongs.datastructure.tree;

public class SumSegmentTree extends SegmentTree<Double> {

    public SumSegmentTree(Double[] items) {
	super(items);
    }

    @Override
    protected Double merge(Double left, Double right) {
	return left + right;
    }

    @Override
    protected Double update(Double curNodeValue, Double oldItem, Double newItem) {
	return curNodeValue + newItem - oldItem;
    }

    public static void main(String[] args) {
	Double items[] = { 1.0, 2.0, 3.0, 4.0, 5.0 };

	SumSegmentTree sTree = new SumSegmentTree(items);
	System.out.println(sTree);

	System.out.println(sTree.query(1, 3));

	sTree.update(2, 10.0);
	System.out.println(sTree.query(1, 3));
    }
}
