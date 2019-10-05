package com.qiutongs.algorithm.search;

/**
 * @author qiutongs
 *
 */
public class BinarySearch {

    public static int search(int[] nums, int target) {
	return binarySearch(target, nums, 0, nums.length - 1);
    }

    public static int searchLeftMost(int[] nums, int target) {
	return binarySearchLeftMost(target, nums, 0, nums.length - 1);
    }

    public static int searchRightMost(int[] nums, int target) {
	return binarySearchRightMost(target, nums, 0, nums.length - 1);
    }

    /**
     * No need to check startIndex == endIndex, as long as you make sure it handles
     * single element properly. The important is middle+1/middle-1. This ensures no
     * double check on boundary.
     * 
     * @param target
     * @param nums
     * @param startIndex
     * @param endIndex
     * @return
     */
    private static int binarySearch(int target, int[] nums, int startIndex, int endIndex) {
	// Termination case: start index > end intex, no need to search
	if (startIndex > endIndex) {
	    return -1;
	}

	// Middle index
	int midIndex = (startIndex + endIndex) / 2;

	if (nums[midIndex] == target) {
	    return midIndex;
	} else if (nums[midIndex] < target) {
	    // +1 is important to avoid endless loop of checking same index
	    return binarySearch(target, nums, midIndex + 1, endIndex);
	} else {
	    // -1 is important to avoid endless loop of checking same index
	    return binarySearch(target, nums, startIndex, midIndex - 1);
	}
    }

    // Find the first position that is equal or greater than target.
    private static int binarySearchLeftMost(int target, int[] nums, int startIndex, int endIndex) {
	if (startIndex > endIndex) {
	    return startIndex;
	}

	int midIndex = (startIndex + endIndex) / 2;

	if (target <= nums[midIndex]) {
	    return binarySearchLeftMost(target, nums, startIndex, midIndex - 1);
	} else {
	    return binarySearchLeftMost(target, nums, midIndex + 1, endIndex);
	}
    }

    // Find the last position that is smaller or equal to target.
    private static int binarySearchRightMost(int target, int[] nums, int startIndex, int endIndex) {
	if (startIndex > endIndex) {
	    return endIndex;
	}

	int midIndex = (startIndex + endIndex) / 2;

	if (target >= nums[midIndex]) {
	    return binarySearchRightMost(target, nums, midIndex + 1, endIndex);
	} else {
	    return binarySearchRightMost(target, nums, startIndex, midIndex - 1);
	}
    }

    public static void main(String[] args) {
	int[] nums = { 1, 3, 5, 7, 9 };
	System.out.println(BinarySearch.search(nums, 7));

	int[] duplicatedNums = { 1, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 9 };
	System.out.println(BinarySearch.searchLeftMost(duplicatedNums, 5));
	System.out.println(BinarySearch.searchRightMost(duplicatedNums, 5));
	System.out.println(BinarySearch.searchLeftMost(duplicatedNums, 4));
	System.out.println(BinarySearch.searchRightMost(duplicatedNums, 8));
	
	int[] arr = { 1, 3, 3, 5 };
	System.out.println(BinarySearch.searchLeftMost(arr, 2));
	System.out.println(BinarySearch.searchRightMost(arr, 4));
    }

}
