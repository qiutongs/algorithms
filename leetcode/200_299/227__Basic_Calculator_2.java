class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        s = s.replaceAll(" ", "");
        
        int ret = 0, num = 0, mul = 0;
        char lastOp = '+';
        for (int i = 0; i <= s.length(); i++) {
            char c = i < s.length() ? s.charAt(i) : '\0';
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else {
                switch(lastOp) {
                    case '+':
                        ret += num;
                        mul = num;
                        break;
                    case '-':
                        ret -= num;
                        mul = -num;
                        break;
                    case '*':
                        ret = ret - mul + mul * num;
                        mul = mul * num;
                        break;
                    case '/':
                        ret = ret - mul + mul / num;
                        mul = mul / num;
                        break;
                }
                num = 0;
                lastOp = c;
            }
        }
        return ret;
    }
}