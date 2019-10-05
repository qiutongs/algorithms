package com.qiutongs.algorithm.query;

import com.qiutongs.bit.Bit;

public class MinRangeQuerySparseTable extends MinRangeQuery {

    // Size is n * log(n)
    // i is the start point of range; j means the range's size is 2 ^ j
    private int[][] sparseTable;

    @Override
    public void initialize(int[] numbers) {

	this.sparseTable = new int[numbers.length][Bit.log(numbers.length) + 1];

	// Bottom-up construction of sparse table.
	for (int j = 0; powerOf2(j) < numbers.length; j++) {
	    for (int i = 0; i + powerOf2(j) - 1 < numbers.length; i++) {
		// If size if 0
		if (j == 0) {
		    sparseTable[i][j] = numbers[i];
		} else {
		    // start = i; end = start + 2 ^ j - 1; middle = start + 2 ^ (j - 1) - 1
		    // [start, end] = min([start, middle], [middle, end])
		    sparseTable[i][j] = Math.min(sparseTable[i][j - 1], sparseTable[i + powerOf2(j - 1)][j - 1]);
		}
	    }
	}
    }

    @Override
    public int queryMin(int rangeStart, int rangeEnd) {
	int j = Bit.log(rangeEnd - rangeStart + 1);

	// start + 2 ^ j - 1 <= end
	// end - 2 ^ j + 1 >= start
	// [start, end] = min([start, start + 2 ^ j - 1], [end - 2 ^ j + 1, end])
	return Math.min(sparseTable[rangeStart][j], sparseTable[rangeEnd - powerOf2(j) + 1][j]);
    }

    @Override
    public void update(int index, int newValue) {
	throw new UnsupportedOperationException();

    }

    private static int powerOf2(int j) {
	return 1 << j;
    }

    public static void main(String[] args) {
	int array[] = { 5, 3, 1, 7, 9 };
	MinRangeQuery mrq = new MinRangeQuerySparseTable();
	mrq.initialize(array);

	System.out.println(mrq.queryMin(0, 1));
	System.out.println(mrq.queryMin(0, 4));
	System.out.println(mrq.queryMin(3, 4));
    }

}
