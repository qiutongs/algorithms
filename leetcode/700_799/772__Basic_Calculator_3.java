class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        s = s.replaceAll(" ", "");
        return dfs(s, new int[]{0});
    }
    
    private int dfs(String s, int[] offset) {
        int num = 0;
        int[] ret = { 0 }, mul = { 0 };
        char lastOp = '+';
        for (;offset[0] <= s.length(); offset[0]++) {
            char c = offset[0] < s.length() ? s.charAt(offset[0]) : '\0';
            if (Character.isDigit(c)) {
                num = num * 10 + (int)(c - '0');
            } else {
                if (c == '(') {
                    offset[0]++;
                    num = dfs(s, offset);
                } else {
                    eval(ret, mul, num, lastOp);
                    num = 0;
                    
                    if (c == '+' || c == '-' || c == '*' || c == '/') {
                        lastOp = c;
                    } else if (c == ')') {
                        break;
                    }
                }
            }
        }
        return ret[0];
    }
    
    private void eval(int[] curVal, int[] mul, int num, char op) {
        switch(op) {
            case '+':
                curVal[0] += num;
                mul[0] = num;
                break;
            case '-':
                curVal[0] -= num;
                mul[0] = -num;
                break;
            case '*':
                curVal[0] = curVal[0] - mul[0] + mul[0] * num;
                mul[0] = mul[0] * num;
                break;
            case '/':
                curVal[0] = curVal[0] - mul[0] + mul[0] / num;
                mul[0] = mul[0] / num;
                break;
        }
    }
}

class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        s = s.replaceAll(" ", "");
        
        int num = 0;
        int[] ret = { 0 }, mul = { 0 };
        char lastOp = '+';
        
        Stack<Integer> numStack = new Stack<>();
        Stack<Integer> mulStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        
        for (int i = 0; i <= s.length(); i++) {
            char c = i < s.length() ? s.charAt(i) : '\0';
            if (Character.isDigit(c)) {
                num = num * 10 + (int)(c - '0');
            } else {
                if (c == '(') {
                    numStack.push(ret[0]);
                    mulStack.push(mul[0]);
                    opStack.push(lastOp);
                    ret[0] = 0;
                    mul[0] = 0;
                    lastOp = '+';
                } else {
                    eval(ret, mul, num, lastOp);
                    num = 0;
                    
                    if (c == '+' || c == '-' || c == '*' || c == '/') {
                        lastOp = c;
                    } else if (c == ')') {
                        num = ret[0];
                        ret[0] = numStack.pop();
                        mul[0] = mulStack.pop();
                        lastOp = opStack.pop();
                    }
                }
            }
        }
        
        return ret[0];
    }
    
    private void eval(int[] curVal, int[] mul, int num, char op) {
        switch(op) {
            case '+':
                curVal[0] += num;
                mul[0] = num;
                break;
            case '-':
                curVal[0] -= num;
                mul[0] = -num;
                break;
            case '*':
                curVal[0] = curVal[0] - mul[0] + mul[0] * num;
                mul[0] = mul[0] * num;
                break;
            case '/':
                curVal[0] = curVal[0] - mul[0] + mul[0] / num;
                mul[0] = mul[0] / num;
                break;
        }
    }
}