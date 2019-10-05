package com.qiutongs.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public int arrangeCoins(int n) {
        int r = 1;
        long coins = 0;
        
        while(fullStairs(r) < n) {
            r *= 2;
        }
        
        int l = r;
        r *= 2;
        
        while(l <= r) {
            int mid = l + (r - l) / 2;
            coins = fullStairs(mid);
            
            // first coins > n
            if (coins > n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return l - 1;
    }
    
    private long fullStairs(long row) {
        return (1 + row) * row / 2;
    }
    
    public static void main(String[] args) {
	Solution s = new Solution();

	int[][] nums = {{9,1},{5,11},{9,7},{2,7},{14,16},{6,16},{0,5},{12,9},{6,5},{9,10},{4,7},{3,2},{10,1},{3,15},{12,4},{10,10},{15,12}};
	
	List<List<Integer>> pairs = new LinkedList<>();
	for (int[] pair : nums) {
	    pairs.add(Arrays.asList(pair[0], pair[1]));
	}
	
	//System.out.println(s.nthUglyNumber(1000000000, 2, 217983653, 336916467));
	System.out.println(s.arrangeCoins(1804289383));
    }
}
