class Solution {
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                numStack.push(num);
                num = 0;
                while(opStack.isEmpty() == false && lessOrEqual(opStack.peek(), c)) {
                    char op = opStack.pop();
                    int num2 = numStack.pop();
                    int num1 = numStack.pop();
                    numStack.push(eval(num1, num2, op));
                }
                opStack.push(c);
            } else if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
        }
        numStack.push(num);
        while(opStack.isEmpty() == false) {
            char op = opStack.pop();
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            numStack.push(eval(num1, num2, op));
        }
        return numStack.pop();
    }
    
    private int eval(int num1, int num2, char op) {
        switch(op) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': return num1 / num2;
        }
        return -1;
    }
    
    // op1 <= op2
    private boolean lessOrEqual(char op1, char op2) {
        return ((op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-')) == false;
    }
}

class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        s = s.replaceAll(" ", "");
        
        int num = 0;
        int[] ret = { 0 };
        int[] mul = { 0 };
        char lastOp = '+';
        for (int i = 0; i <= s.length(); i++) {
            char c = i < s.length() ? s.charAt(i) : '\0';
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else {
                eval(ret, mul, num, lastOp);
                num = 0;
                lastOp = c;
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