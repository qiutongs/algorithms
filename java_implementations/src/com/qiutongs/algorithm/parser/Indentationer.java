package com.qiutongs.algorithm.parser;



public class Indentationer {
    
    private static String INDENT = "    ";
    
    public static void print(String input) {
	dfs(input, new int[] {0}, 0);
    }
    
    private static void dfs(String input, int[] offset, int level) {
	StringBuilder word = new StringBuilder();
	for (; offset[0] <= input.length(); offset[0]++) {
	    char c = offset[0] < input.length() ? input.charAt(offset[0]) : '\0';
	    if (Character.isAlphabetic(c)) {
		word.append(c);
	    } else {
		if (word.length() > 0) {
		    printIndent(level);
		    System.out.println(word.toString());
		    word.setLength(0);
		}
		if (c == '(') {
		    printIndent(level);
		    System.out.println('(');
		    offset[0]++;
		    dfs(input, offset, level + 1);
		    printIndent(level);
		    System.out.println(')');
		} else if (c == ')') {
		    break;
		}
	    }
	}
    }
    
    private static void printIndent(int level) {
	for (int i = 0; i < level; i++) {
	    System.out.print(INDENT);
	}
    }
    
    public static void main(String[] args) {
	print("(hello word (bye bye))");
    }
}
