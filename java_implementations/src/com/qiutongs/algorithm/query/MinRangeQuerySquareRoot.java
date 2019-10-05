package com.qiutongs.algorithm.query;

public class MinRangeQuerySquareRoot extends MinRangeQuery {

    private int[] blocks;
    private int[] numbers;

    private int blockSize;

    @Override
    public void initialize(int[] numbers) {
	this.blockSize = getSquareRoot(numbers.length);
	this.numbers = numbers;
	this.blocks = new int[numbers.length / blockSize + 1];

	for (int i = 0; i < numbers.length; i++) {
	    int blockIndex = i / blockSize;

	    if (i % blockSize == 0) {
		this.blocks[blockIndex] = numbers[i];
	    } else {
		this.blocks[blockIndex] = Math.min(this.blocks[blockIndex], numbers[i]);
	    }
	}
    }

    @Override
    public int queryMin(int rangeStart, int rangeEnd) {
	int blockStart = rangeStart / blockSize;
	int blockEnd = rangeEnd / blockSize;
	int min = Integer.MAX_VALUE;

	if (blockStart == blockEnd) {
	    min = getMinInNumbers(rangeStart, rangeEnd);
	} else {
	    // Get min out of the middle blocks.
	    for (int i = blockStart + 1; i <= blockEnd - 1; i++) {
		min = Math.min(min, blocks[i]);
	    }

	    // Get min out of the left sub block.
	    if (rangeStart % blockSize == 0) {
		min = Math.min(min, blocks[blockStart]);
	    } else {
		min = getMinInNumbers(rangeStart, blockSize * (blockStart + 1));
	    }

	    // Get min out of the right sub block.
	    if (rangeEnd % blockSize == 0) {
		min = Math.min(min, blocks[blockEnd]);
	    } else {
		min = getMinInNumbers(blockSize * (blockEnd - 1) + 1, rangeEnd);
	    }
	}

	return min;
    }

    @Override
    public void update(int index, int newValue) {
	this.numbers[index] = newValue;
	this.blocks[index / blockSize] = Math.min(this.blocks[index / blockSize], newValue);
    }

    private int getMinInNumbers(int start, int end) {
	int min = Integer.MAX_VALUE;
	for (int i = start; i < end; i++) {
	    min = Math.min(min, numbers[i]);
	}
	return min;
    }

    private static int getSquareRoot(int n) {
	return (int) Math.ceil(Math.sqrt(n));
    }

    public static void main(String[] args) {
	int array[] = { 5, 3, 1, 7, 9 };
	MinRangeQuery mrq = new MinRangeQuerySquareRoot();
	mrq.initialize(array);

	System.out.println(mrq.queryMin(0, 1));
	System.out.println(mrq.queryMin(0, 4));
	System.out.println(mrq.queryMin(3, 4));

	mrq.update(2, -1);
	System.out.println(mrq.queryMin(0, 4));
    }

}
