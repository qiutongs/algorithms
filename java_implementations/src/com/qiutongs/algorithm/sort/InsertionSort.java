package com.qiutongs.algorithm.sort;

import java.util.Arrays;

public class InsertionSort {

    public static void sort(int[] nums) {
	// iterate from 1 to n-1
	for (int i = 1; i < nums.length; i++) {
	    // compare current number nums[i] from i-1 to 0
	    // swap if current number is smaller
	    for (int j = i; j > 0; j--) {
		if (nums[j] < nums[j - 1]) {
		    swap(nums, j, j - 1);
		} else {
		    break;
		}
	    }
	}
    }

    private static void swap(int[] nums, int i1, int i2) {
	int tmp = nums[i1];
	nums[i1] = nums[i2];
	nums[i2] = tmp;
    }

    public static void main(String[] args) {
	int nums[] = { 3, 6, 1, 8, 7 };
	InsertionSort.sort(nums);
	System.out.println(Arrays.toString(nums));
    }

}
