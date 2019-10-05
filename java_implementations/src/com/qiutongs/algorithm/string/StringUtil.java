package com.qiutongs.algorithm.string;

public class StringUtil {

    public static String reverseSentence(String s) {
	char[] sChars = s.toCharArray();

	reverse(sChars, 0, sChars.length - 1);

	// Initialize to -1 as not pointed to any character.
	int start = -1;
	for (int i = 0; i <= sChars.length; i++) {
	    if (i == sChars.length || sChars[i] == ' ') {
		if (start != -1) {
		    // If find a space character or end of sentence and "start" has been set,
		    // reverse the word and clear the "start".
		    reverse(sChars, start, i - 1);
		    start = -1;
		}
	    } else {
		if (start == -1) {
		    // If find a character and "start" has not been set, set it.
		    start = i;
		}
	    }
	}

	return new String(sChars);
    }

    private static void reverse(char[] string, int start, int end) {
	while (start < end) {
	    char tmp = string[start];
	    string[start] = string[end];
	    string[end] = tmp;

	    start++;
	    end--;
	}
    }

    public static void main(String[] args) {
	System.out.println(StringUtil.reverseSentence("abcdefg  hijklmnopq   987654321  "));
    }

}
