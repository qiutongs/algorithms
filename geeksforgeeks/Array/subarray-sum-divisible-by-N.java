/*
Brute force: 

Time complexity: O(n^3)
Space complexity: O(1)

Prefix-sum:

Time complexity: O(n^2)
Space complexity: O(n)

Improvement on prefix-sum: modulo each prefix-sum by n and group them by 0...n-1. If there are at least two in same group, then it means prefixSum[j] - prefixSum[i] is divisible by n

Ref: https://www.geeksforgeeks.org/find-a-subarray-whose-sum-is-divisible-by-size-of-the-array/
*/

bool isSubArrayDivisible(int[] array, int n) {
	int prefixSum = 0;
	ArrayList<LinkedList<Integer>> prefixSumModArray = new ArrayList<>(n);

    for (int i = 0; i < n; i++) {
        prefixSumModArray.add(new LinkedList<Integer>());
    }


	for (int i = 0;i < array.length; i++) {
	    prefixSum += array[i];
	    prefixSumModArray.get(prefixSum % n).add(i);
	}

	for (int i = 0; i < n; i++) {
	    if (prefixSumModArray.get(i).size() >= 2) {
	        return true;
	    }
	}

	return false;
}