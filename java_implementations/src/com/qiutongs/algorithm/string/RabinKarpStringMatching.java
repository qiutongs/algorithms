package com.qiutongs.algorithm.string;

/**
 * Linear expected-time, based on hashing
 */
public class RabinKarpStringMatching {

    private static final int BASE = 31;

    /**
     * 1. compute hash on both the pattern p and m-character substring starting from
     * ith of t.
     * 
     * 2. if they are identical, the hash must be the same
     * 
     * 3. if they are different, the hash value almost different
     * 
     * 4. Final verification: compare the two m-character strings again
     * 
     * Hash(T, i) = BASE * (Hash(T, i-1) - BASE^(m-1) * T[i-1]) + T[i-1+m]
     * 
     * @param text
     * @param pattern
     * @return
     */
    public static int match(String text, String pattern) {
	int n = text.length(), m = pattern.length();
	int highestCoefficient = (int) Math.pow(BASE, m - 1);

	int hashCodeT = 0;
	// One-time computation of hashcode of pattern
	int hashCodeP = hashCode(pattern, m);

	for (int i = 0; i <= n - m; i++) {
	    // One-time computation of hashcode of text at index.
	    // The computation after that is iterative
	    hashCodeT = i == 0 ? hashCode(text, m)
		    : BASE * (hashCodeT - highestCoefficient * text.charAt(i - 1)) + text.charAt(i - 1 + m);

	    if (hashCodeT == hashCodeP && match(text, i, pattern)) {
		return i;
	    }
	}

	return -1;
    }

    private static int hashCode(String s, int length) {
	int result = 0;

	for (int i = 0; i < length; i++) {
	    result = result * BASE + s.charAt(i);
	}

	return result;
    }

    /**
     * If substring of text and pattern match
     * 
     * @param text
     * @param startIndexOfText
     * @param pattern
     * @return
     */
    private static boolean match(String text, int startIndexOfText, String pattern) {
	for (int j = 0; j < pattern.length(); j++) {
	    if (text.charAt(startIndexOfText + j) != pattern.charAt(j)) {
		return false;
	    }
	}
	return true;
    }

    public static void main(String[] args) {
	System.out.println(RabinKarpStringMatching.match("a", "a"));
	System.out.println(RabinKarpStringMatching.match("ba", "a"));
	System.out.println(RabinKarpStringMatching.match("ajhsdjkfhkahdsfh", "jkf"));
	System.out.println(RabinKarpStringMatching.match("ababcaababcaabc", "ababcaabc"));
    }
}
