package com.qiutongs.algorithm.query;

public class MinRangeQueryLookupTable extends MinRangeQuery {

    private int lookupTable[][];

    @Override
    public void initialize(int[] numbers) {
	this.lookupTable = new int[numbers.length][numbers.length];
	buildLookupTable(numbers);

    }

    @Override
    public int queryMin(int rangeStart, int rangeEnd) {
	return lookupTable[rangeStart][rangeEnd];
    }

    @Override
    public void update(int index, int newValue) {
	if (newValue >= lookupTable[index][index]) {
	    return;
	}

	for (int i = 0; i < lookupTable.length; i++) {
	    for (int j = i; j < lookupTable[i].length; j++) {
		if (i <= index && index <= j) {
		    lookupTable[i][j] = newValue < lookupTable[i][j] ? newValue : lookupTable[i][j];
		}
	    }
	}

    }

    // Only half of the space is used
    private void buildLookupTable(int[] numbers) {

	for (int i = 0; i < numbers.length; i++) {
	    lookupTable[i][i] = numbers[i];
	    int min = numbers[i];
	    for (int j = i + 1; j < numbers.length; j++) {
		if (numbers[j] < min) {
		    min = numbers[j];
		}

		lookupTable[i][j] = min;
	    }
	}
    }

    public static void main(String[] args) {
	int array[] = { 5, 3, 1, 7, 9 };
	MinRangeQuery mrq = new MinRangeQueryLookupTable();
	mrq.initialize(array);

	System.out.println(mrq.queryMin(0, 1));
	System.out.println(mrq.queryMin(0, 4));
	System.out.println(mrq.queryMin(3, 4));

	mrq.update(2, -1);
	System.out.println(mrq.queryMin(0, 4));
    }
}
