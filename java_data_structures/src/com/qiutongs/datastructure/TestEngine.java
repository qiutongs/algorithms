package com.qiutongs.datastructure;

public class TestEngine {
    static final int[] FULL_DATA_SET = { 100, 1000, 10000, 100000, 200000, 400000 };
    static final int[] SMALL_DATA_SET = { 100, 1000, 10000 };

    // Each element is the amount of data in one group of tests.
    private final int[] dataSet;

    // Data structure to test
    private final DataStructure dataStructure;

    public TestEngine(int[] dataSet, DataStructure dataStructure) {
        this.dataSet = dataSet;
        this.dataStructure = dataStructure;
    }

    public void run() {
        double[][] result = new double[dataSet.length][];

        for (int j = 0; j < dataSet.length; j++) {
            result[j] = dataStructure.test(dataSet[j]);
        }

        output(result);
    }

    // Output a formatted string (Youdao Note)
    private void output(double[][] result) {
        StringBuilder sb = new StringBuilder();

        // First row (table header)
        dataStructure.getOperations().forEach(name -> sb.append(" # |  " + name));
        sb.append('\n');

        // Second row (horizontal line)
        sb.append("---");
        dataStructure.getOperations().forEach(name -> sb.append("|---"));
        sb.append('\n');

        // Actual content
        for (int i = 0; i < result.length; i++) {
            // First column: number of data
            sb.append(dataSet[i]);

            for (int j = 0; j < result[i].length; j++) {
                sb.append(" | ");
                sb.append((int) result[i][j]);
            }

            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        DataStructureArray array = new DataStructureArray();
        TestEngine engine = new TestEngine(FULL_DATA_SET, array);

        engine.run();
    }

}
