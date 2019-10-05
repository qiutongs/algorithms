package com.qiutongs.datastructure.array;

public class DynamicArray<I> implements Array<I> {
    private static final int INITIAL_CAPACITY = 1;
    private static final int SCALE_FACTOR = 2;

    protected Object[] array;
    protected int size;
    private int capacity;

    public DynamicArray() {
        this(INITIAL_CAPACITY);
    }

    public DynamicArray(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    @Override
    public void add(I item) {
        if (size == capacity) {
            scaleUp();
        }

        array[size++] = item;
    }

    @Override
    public void add(int index, I item) {
        if (size == capacity) {
            scaleUp();
        }

        rightShift(index, size);
        array[index] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    public I get(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException();
        }

        return (I) array[index];
    }

    public void set(int index, I value) {
        if (index < 0 || index >= size) {
            throw new RuntimeException();
        }

        array[index] = value;
    }

    public int search(I item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    public I remove(int index) {
        I result = get(index);

        swap(index, size - 1);
        size--;

        return result;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        if (size > 0) {
            sb.append(array[0]);
            for (int i = 1; i < size; i++) {
                sb.append(", ");
                sb.append(array[i]);
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    protected void swap(int index1, int index2) {
        I tmp = (I) array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    protected void leftShift(int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex - 1; i++) {
            array[i] = array[i + 1];
        }
    }

    protected void rightShift(int startIndex, int endIndex) {
        for (int i = endIndex; i > startIndex; i--) {
            array[i] = array[i - 1];
        }
    }

    protected void scaleUp() {
        capacity = capacity == 0 ? 1 : capacity * SCALE_FACTOR;
        Object[] newArray = new Object[this.capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public static void main(String[] args) {
        System.out.println("-------- Dynamic Capacity Illustration --------  ");
        DynamicArray<Integer> array = new DynamicArray<>();
        System.out.println("Initial size: " + array.size());
        System.out.println("Initial capacity: " + array.capacity());

        array.add(3);
        System.out.println("Size after adding one number: " + array.size());
        System.out.println("Capacity after adding one number: " + array.size());

        array.add(12);
        System.out.println("Capacity after adding two numbers: " + array.capacity());

        array.add(26);
        System.out.println("Capacity after adding three numbers: " + array.capacity());
        System.out.println("Size after adding three numbers: " + array.size());

        array.add(56);
        array.add(65);
        System.out.println("Capacity after adding five numbers: " + array.capacity());
        System.out.println("Size after adding five numbers: " + array.size());

        System.out.println("-------- Basic add, get, set, search --------  ");

        System.out.println("Search existed element: " + array.search(3));
        System.out.println("Search not existed element: " + array.search(2));

        System.out.println("Number in index 0: " + array.get(0));
        array.set(0, 6);
        System.out.println("Number in index 0, after set: " + array.get(0));
        System.out.println("Search old element: " + array.search(3));
        System.out.println("Search new element: " + array.search(6));

        System.out.println(array);
    }
}
