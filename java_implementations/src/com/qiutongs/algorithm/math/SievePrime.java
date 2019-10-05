package com.qiutongs.algorithm.math;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SievePrime {

    // O(nloglogn)
    // Ref: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
    public static List<Integer> findAllPrimes(int n) {
	List<Integer> result = new LinkedList<>();

	// 0: prime number, 1: composite number
	int[] numFlags = new int[n + 1];
	Arrays.fill(numFlags, 0);

	// Start with smallest prime number
	int curNum = 2;
	
	while (curNum <= n) {
	    // If this is a prime number
	    if (numFlags[curNum] == 0) {
		// Set flag to 1 for all of its multiple
		int sum = curNum + curNum;

		while (sum <= n) {
		    numFlags[sum] = 1;
		    sum += curNum;
		}
	    }

	    curNum++;
	}

	for (int i = 2; i <= n; i++) {
	    if (numFlags[i] == 0) {
		result.add(i);
	    }
	}

	return result;
    }

    public static void main(String[] args) {
	System.out.println(findAllPrimes(50));
    }
}
