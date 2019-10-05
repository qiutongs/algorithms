package com.qiutongs.algorithm.query;

import com.qiutongs.datastructure.tree.MinSegmentTree;

public class MinRangeQuerySegmentTree extends MinRangeQuery {

    private MinSegmentTree<Integer> segmentTree;

    @Override
    public void initialize(int[] numbers) {
	Integer[] numbersI = new Integer[numbers.length];
	for (int i = 0; i < numbers.length; i++) {
	    numbersI[i] = numbers[i];
	}
	segmentTree = new MinSegmentTree<>(numbersI);
    }

    @Override
    public int queryMin(int rangeStart, int rangeEnd) {
	return segmentTree.query(rangeStart, rangeEnd);
    }

    @Override
    public void update(int index, int newValue) {
	segmentTree.update(index, newValue);
    }

    public static void main(String[] args) {
	int array[] = { 5, 3, 1, 7, 9 };
	MinRangeQuery mrq = new MinRangeQuerySegmentTree();
	mrq.initialize(array);

	System.out.println(mrq.queryMin(0, 1));
	System.out.println(mrq.queryMin(0, 4));
	System.out.println(mrq.queryMin(3, 4));
	
	mrq.update(2, -1);
	System.out.println(mrq.queryMin(0, 4));
    }
}
