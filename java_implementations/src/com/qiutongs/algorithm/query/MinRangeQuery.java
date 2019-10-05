package com.qiutongs.algorithm.query;

public abstract class MinRangeQuery {

    public abstract void initialize(int numbers[]);

    public abstract int queryMin(int rangeStart, int rangeEnd);

    public abstract void update(int index, int newValue);
}
