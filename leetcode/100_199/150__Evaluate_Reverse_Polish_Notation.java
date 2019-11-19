class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (isOperator(tokens[i])) {
                int op2 = stack.pop();
                int op1 = stack.pop();
                stack.push(evaluate(op1, op2, tokens[i]));
            } else {
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.peek();
    }
    
    private int evaluate(int op1, int op2, String operator) {
        switch(operator) {
            case "+": return op1 + op2;
            case "-": return op1 - op2; 
            case "*": return op1 * op2;
            case "/": return op1 / op2;
        }
        return 0;
    }
    
    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
}