package com.qiutongs.datastructure;

import java.util.Random;

import com.qiutongs.datastructure.array.DynamicArray;

public class DataStructureArray extends DataStructure {

    private static final int REPEAT_COUNT = 100000;

    private final Random rand;

    private DynamicArray<Integer> array;

    public DataStructureArray() {
        this.rand = new Random();

        register("Get");
        register("Search");
        register("Append");
    }

    @Override
    public double[] test(int dataCount) {
        double[] result = new double[operationList.size()];
        double start = 0, end = 0;

        for (int i = 0; i < operationList.size(); i++) {
            switch (operationList.get(i)) {
            case "Get":
                setup(dataCount);
                start = System.nanoTime();
                for (int j = 0; j < REPEAT_COUNT; j++) {
                    array.get(rand.nextInt(array.size()));
                }
                end = System.nanoTime();
                result[i] = (end - start) / (double) REPEAT_COUNT;
                break;

            case "Search":
                setup(dataCount);
                start = System.nanoTime();
                for (int j = 0; j < REPEAT_COUNT; j++) {
                    array.search(rand.nextInt());
                }
                end = System.nanoTime();
                result[i] = (end - start) / (double) REPEAT_COUNT;
                break;

            case "Append":
                setup(0);
                start = System.nanoTime();
                for (int j = 0; j < dataCount; j++) {
                    array.add(rand.nextInt());
                }
                end = System.nanoTime();
                result[i] = (end - start) / (double) dataCount;
                break;
            }
        }

        return result;
    }

    private void setup(int dataCount) {
        // Discard previous array and create new object
        array = new DynamicArray<>(dataCount);

        for (int i = 0; i < dataCount; i++) {
            array.add(rand.nextInt());
        }
    }
}
