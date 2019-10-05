package com.qiutongs.datastructure;

import java.util.ArrayList;

public abstract class DataStructure {
    // A data structure holds a list of operations.
    protected final ArrayList<String> operationList;

    public DataStructure() {
        this.operationList = new ArrayList<>();
    }

    public ArrayList<String> getOperations() {
        return operationList;
    }

    protected void register(String operation) {
        operationList.add(operation);
    }

    public abstract double[] test(int dataCount);
}
