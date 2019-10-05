package com.qiutongs.bit;

public class Bit {

    public static int roundUpToPowerOfTwo(int n) {
	n--;
	n |= n >> 1;
	n |= n >> 2;
	n |= n >> 4;
	n |= n >> 8;
	n |= n >> 16;

	n++;
	return n;
    }

    public static int log(int n) {
	int result = 0;
	if (n >> 16 > 0) {
	    n >>= 16;
	    result += 16;
	}
	if (n >> 8 > 0) {
	    n >>= 8;
	    result += 8;
	}
	if (n >> 4 > 0) {
	    n >>= 4;
	    result += 4;
	}
	if (n >> 2 > 0) {
	    n >>= 2;
	    result += 2;
	}
	if (n >> 1 > 0) {
	    n >>= 1;
	    result += 1;
	}
	return result;
    }

    public static int reverse(int n) {
	int result = 0;

	while (n > 0) {
	    result = (result << 1) + (n & 1);
	    n >>>= 1;
	}

	return result;
    }

    public static void main(String[] args) {
	System.out.println(Bit.roundUpToPowerOfTwo(9));
	System.out.println(Bit.roundUpToPowerOfTwo(64));
	System.out.println(Bit.roundUpToPowerOfTwo(65));

	System.out.println(Bit.log(8));
	System.out.println(Bit.log(9));
	System.out.println(Bit.log(17));
	System.out.println(Bit.log(1028));
	
	System.out.println(Integer.toBinaryString(44));
	System.out.println(Integer.toBinaryString(Bit.reverse(44)));
    }
}
