package com.qiutongs.datastructure.array;

public class SortedDynamicArray<I extends Comparable<I>> extends DynamicArray<I> implements SortedArray<I> {

    @Override
    public void add(I item) {
        int index = binarySearch(item, 0, size - 1);

        index = index < 0 ? -index - 1 : index;
        super.add(index, item);
    }

    @Override
    public int search(I item) {
        return binarySearch(item, 0, size - 1);
    }

    @Override
    public I remove(int index) {
        I result = get(index);

        leftShift(index, size);
        size--;

        return result;
    }

    @SuppressWarnings("unchecked")
    private int binarySearch(I item, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return -(startIndex + 1);
        }

        int midIndex = (startIndex + endIndex) / 2;

        int compareResult = ((I) array[midIndex]).compareTo(item);

        if (compareResult == 0) {
            return midIndex;
        } else if (compareResult < 0) {
            return binarySearch(item, midIndex + 1, endIndex);
        } else {
            return binarySearch(item, startIndex, midIndex - 1);
        }
    }

    public static void main(String[] args) {
        SortedDynamicArray<Integer> array = new SortedDynamicArray<>();

        array.add(1);
        System.out.println(array);

    }
}
