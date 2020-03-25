class MaxStack {
    private Stack<Integer> valStack = new Stack<>();
    private Stack<Integer> maxStack = new Stack<>();
    
    public MaxStack() {
        // do intialization if necessary
    }

    /*
     * @param number: An integer
     * @return: nothing
     */    
    public void push(int x) {
        valStack.push(x);
        if (maxStack.isEmpty() || maxStack.peek() <= x) {
            maxStack.push(x);
        } else {
            maxStack.push(maxStack.peek());
        }
    }

    public int pop() {
        maxStack.pop();
        return valStack.pop();
    }

    /*
     * @return: An integer
     */    
    public int top() {
        return valStack.peek();
    }

    /*
     * @return: An integer
     */    
    public int peekMax() {
        return maxStack.peek();
    }

    /*
     * @return: An integer
     */    
    public int popMax() {
        Stack<Integer> tmp = new Stack<>();
        while(valStack.peek().intValue() != maxStack.peek().intValue()) {
            tmp.push(valStack.pop());
            maxStack.pop();
        }
        
        valStack.pop();
        int ret = maxStack.pop();
        
        while(tmp.isEmpty() == false) {
            push(tmp.pop());
        }
        return ret;
    }
}