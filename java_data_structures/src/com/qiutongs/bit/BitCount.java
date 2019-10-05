package com.qiutongs.bit;

public class BitCount {

    // O(log(n))
    public static int oneBits_1(int n) {
	int result = 0;

	while (n > 0) {
	    result += n & 1;
	    n >>= 1;
	}

	return result;
    }

    // Brian Kernighan's algorithm
    // O(log(n))
    public static int oneBits_2(int n) {
	int result = 0;

	while (n > 0) {
	    result += 1;
	    n = n & (n - 1);
	}

	return result;
    }

    // Java Bit Count
    // O(1)
    public static int oneBits_3(int n) {
	n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
	n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
	n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
	n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
	n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
	return n;
    }

    public static void main(String[] args) {
	System.out.println(BitCount.oneBits_1(30));
	System.out.println(BitCount.oneBits_2(30));
	System.out.println(BitCount.oneBits_3(30));
    }
}
