class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        s = s.replaceAll(" ", "");
        return dfs(s, new int[]{0});
    }
    
    private int dfs(String s, int[] offset) {
        int ret = 0;
        int sign = 1;
        int num = 0;
        for (;offset[0] <= s.length(); offset[0]++) {
            char c = offset[0] < s.length() ? s.charAt(offset[0]) : '\0';
            if (Character.isDigit(c)) {
                num = num * 10 + (int)(c - '0');
            } else {
                if (c == '(') {
                    offset[0]++;
                    num = dfs(s, offset);
                } else {
                    ret += sign * num;
                    num = 0;
                    
                    if (c == '+' || c == '-') {
                        sign = c == '+' ? 1 : -1;
                    } else if (c == ')') {
                        break;
                    }
                }
            }
        }
        return ret;
    }
}

// Stack
class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        
        s = s.replaceAll(" ", "");
        
        Stack<Integer> numStack = new Stack<>();
        Stack<Integer> signStack = new Stack<>();
        int ret = 0;
        int num = 0;
        int sign = 1;
        for (int i = 0; i <= s.length(); i++) {
            char c = i < s.length()? s.charAt(i) : '\0';
            if (Character.isDigit(c)) {
                num = num * 10 + (int)(c - '0');
            } else if (c == '(') {
                numStack.push(ret);
                signStack.push(sign);
                ret = 0;
                sign = 1;
            } else {
                ret += sign * num;
                num = 0;
                
                if (c == '+' || c == '-') {
                    sign = c == '+' ? 1 : -1;
                } else if (c == ')') {
                    num = ret;
                    ret = numStack.pop();
                    sign = signStack.pop();
                }
            }
        }
        return ret;
    }
}