class Solution {
    public String removeKdigits(String num, int k) {
        if (num == null || num.isEmpty()) {
            return num;
        }
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while(i < num.length()) {
            char c = num.charAt(i);
            while(stack.isEmpty() == false && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
            i++;
        }
        while(k > 0) {
            stack.pop();
            k--;
        }
        
        return toString(stack);
    }
    
    private String toString(Stack<Character> stack) {
        char[] ret = new char[stack.size()];
        for (int j = ret.length - 1; j >= 0; j--) {
            ret[j] = stack.pop();
        }
        
        int zeroIndex = 0;
        while(zeroIndex < ret.length && ret[zeroIndex] == '0') {
            zeroIndex++;
        }
        
        if (zeroIndex == ret.length) {
            return "0";
        } else {
            return new String(ret, zeroIndex, ret.length - zeroIndex);
        }
    }
}