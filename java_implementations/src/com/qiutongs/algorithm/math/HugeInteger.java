package com.qiutongs.algorithm.math;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * @author qiutongs
 */
public class HugeInteger implements Comparable<HugeInteger> {
    // Holds the individual digits of the number in reverse order
    // For example: number is 1234567; this array is 7, 6, 5, 4, 3, 2, 1
    private final ArrayList<Integer> digits;

    public static ArrayList<Integer> convertStringToDigits(String number) {
        ArrayList<Integer> digits = null;

        // Edge case: behavior is TBD
        if (number.isEmpty()) {
            digits = new ArrayList<>();
            digits.add(0);
            return digits;
        }

        // Validate each character is digit
        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new InputMismatchException("");
            }
        }

        // Pre-allocate the capacity
        digits = new ArrayList<>(number.length());

        for (int i = number.length() - 1; i >= 0; i--) {
            digits.add(number.charAt(i) - '0');
        }

        return digits;
    }

    public static String convertDigitsToString(ArrayList<Integer> digits) {
        int size = digits.size();
        char[] resultChars = new char[size];
        for (int i = 0; i < size; i++) {
            resultChars[i] = (char) (digits.get(size - 1 - i) + '0');
        }
        return new String(resultChars);
    }

    /**
     * Initializes the HugeInteger to 0 (one digit with the value 0)
     */
    public HugeInteger() {
        digits = new ArrayList<>();
        digits.add(0);
    }

    /**
     * Initializes a HugeInteger to the String of digits in s and throws an
     * InputMismatchException if there are characters in s that are not digits
     * 
     * @param s
     */
    public HugeInteger(String s) {
        this.digits = convertStringToDigits(s);
    }

    /**
     * Initializes this HugeInteger to be the same as the value of the integer i
     * 
     * @param i
     */
    public HugeInteger(int i) {
        digits = new ArrayList<>();

        if (i == 0) {
            digits.add(0);
        } else {
            while (i > 0) {
                digits.add(i % 10);
                i /= 10;
            }
        }
    }

    /**
     * Tests if this is equal to zero (note that 0 == 00 == 0000).
     * 
     * @return
     */
    boolean isZero() {
        return digits.size() == 1 && digits.get(0) == 0;
    }

    /**
     * Adds this to rhs , and returns the result as a new HugeInteger . The
     * method does not change this or rhs
     * 
     * @param rhs
     * @return
     */
    HugeInteger add(HugeInteger rhs) {
        ArrayList<Integer> rhsDigits = convertStringToDigits(rhs.toString());

        ArrayList<Integer> resultDigits = new ArrayList<>(Math.max(this.digits.size(), rhsDigits.size()) + 1);

        int p = 0;
        int carry = 0;

        while (p < this.digits.size() && p < rhsDigits.size()) {
            int total = this.digits.get(p) + rhsDigits.get(p) + carry;
            carry = total / 10;

            resultDigits.add(total % 10);
            p++;
        }
        
        while (p < this.digits.size()) {
            int total = this.digits.get(p) + carry;
            carry = total / 10;

            resultDigits.add(total % 10);
            p++;
        }

        while (p < rhsDigits.size()) {
            int total = rhsDigits.get(p) + carry;
            carry = total / 10;

            resultDigits.add(total % 10);
            p++;
        }

        if (carry == 1) {
            resultDigits.add(1);
        }

        return new HugeInteger(convertDigitsToString(resultDigits));
    }

    /*
     * Returns a String representation of this HugeInteger
     */
    @Override
    public String toString() {
        return convertDigitsToString(digits);
    }

    /*
     * Compares two HugeInteger s, and returns a negative integer, zero, or a
     * positive integer as this object is less than, equal to, or greater than
     * other
     */
    @Override
    public int compareTo(HugeInteger other) {
        if (this.toString().length() > other.toString().length()) {
            return 1;
        } else if (this.toString().length() < other.toString().length()) {
            return -1;
        } else {
            return this.toString().compareTo(other.toString());
        }
    }

    /*
     * Return true if this HugeInteger is equal to HugeInteger other and false
     * otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof HugeInteger)) {
            return false;
        }

        return compareTo((HugeInteger) o) == 0;
    }

    public static void main(String[] args) {
        HugeInteger hi1 = new HugeInteger();
        HugeInteger hi2 = new HugeInteger("1234567");
        HugeInteger hi3 = new HugeInteger(7654321);
        HugeInteger hi4 = new HugeInteger(1234567);

        System.out.println(hi1.toString());
        System.out.println(hi2.toString());
        System.out.println(hi3.toString());

        System.out.println(hi2.compareTo(hi3));
        System.out.println(hi2.compareTo(hi4));

        System.out.println(hi3.add(hi4));
        System.out.println(new HugeInteger(1).add(new HugeInteger("999999999999")));
        
        nthUglyNumber(6);
    }
    
    public static int nthUglyNumber(int n) {
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        
        int pTwo = 0, pThree = 0, pFive = 0;
        
        for (int i = 1; i < n; i++) {
            int nextTwo = uglyNums[pTwo] * 2;
            int nextThree = uglyNums[pThree] * 3;
            int nextFive = uglyNums[pFive] * 5;
            
            if (nextTwo < Math.min(nextThree, nextFive)) {
                uglyNums[i] = nextTwo;
                pTwo++;
            } else if (nextThree < Math.min(nextTwo, nextFive)) {
                uglyNums[i] = nextThree;
                pThree++;
            } else {
                uglyNums[i] = nextFive;
                pFive++;
            }
        }
        
        return uglyNums[n-1];
    }

}