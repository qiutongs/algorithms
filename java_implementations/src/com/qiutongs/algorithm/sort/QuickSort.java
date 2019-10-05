package com.qiutongs.algorithm.sort;

import java.util.Arrays;

public class QuickSort {

    public static void sort(int[] nums) {
	sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int start, int end) {
	if (start >= end) {
	    return;
	}

	int pivotIndex = partition(nums, start, end);
	sort(nums, start, pivotIndex - 1);
	sort(nums, pivotIndex + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
	int p = end;

	int firstLarger = start;

	for (int i = start; i < end; i++) {
	    if (nums[i] < nums[p]) {
		swap(nums, i, firstLarger);
		firstLarger++;
	    }
	}

	swap(nums, firstLarger, p);

	return firstLarger;
    }

    private static void swap(int[] nums, int i1, int i2) {
	int tmp = nums[i1];
	nums[i1] = nums[i2];
	nums[i2] = tmp;
    }

    public static void main(String[] args) {
	int nums[] = { 3, 6, 1, 8, 7 };
	QuickSort.sort(nums);
	System.out.println(Arrays.toString(nums));
    }
}
