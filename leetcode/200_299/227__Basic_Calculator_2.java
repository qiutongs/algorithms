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