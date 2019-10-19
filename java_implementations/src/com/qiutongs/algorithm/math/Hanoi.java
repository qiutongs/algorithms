package com.qiutongs.algorithm.math;

public class Hanoi {
    
    public static void move(int n) {
	move(n, 1, 2, 3);
    }
    
    private static void move(int disk, int current, int empty, int target) {
	if (disk == 1) {
	    print(disk, current, target);
	    return;
	}
	
	move(disk - 1, current, target, empty);
	print(disk, current, target);
	move(disk - 1, empty, current, target);
    }
    
    private static void print(int disk, int current, int target) {
	System.out.println(String.format("move disk %d from needle %d to needle %d", disk, current, target));
    }
    
    public static void main(String[] args) {
	move(3);
    }
}