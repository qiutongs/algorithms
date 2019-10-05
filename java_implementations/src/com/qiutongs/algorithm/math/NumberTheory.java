package com.qiutongs.algorithm.math;

public class NumberTheory {

    public static int gcd(int a, int b) {
	if (b == 0) {
	    return a;
	}

	return gcd(b, a % b);
    }
    
    public static int lcm(int a, int b) {
	return a / gcd(a,b) * b;
    }
    
    public static void main(String[] args) {
	System.out.println(gcd(252, 105));
	System.out.println(gcd(105, 252));
	System.out.println(lcm(105, 252));
    }
}
