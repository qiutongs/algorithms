// https://www.youtube.com/watch?v=gxYV8eZY0eQ&t=706s
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> subLeft = diffWaysToCompute(input.substring(0, i));
                List<Integer> subRight = diffWaysToCompute(input.substring(i + 1, input.length()));
                for (Integer num1 : subLeft) {
                    for (Integer num2 : subRight) {
                        ret.add(eval(num1, num2, c));
                    }
                }
            }
        }
        if (ret.isEmpty()) {
            ret.add(Integer.valueOf(input));
        }
        return ret;
    }
    
    private int eval(int num1, int num2, char op) {
        switch(op) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
        }
        throw new RuntimeException();
    }
}