class MyStack {
    private Queue<Integer> pushQ = new LinkedList<>();
    private Queue<Integer> auxQ = new LinkedList<>();
    
    /** Initialize your data structure here. */
    public MyStack() {
        
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        pushQ.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return transfer();
    }
    
    /** Get the top element. */
    public int top() {
        int ret = transfer();
        pushQ.offer(ret);
        return ret;
    }
    
    private int transfer() {
        int size = pushQ.size();
        while(size > 1) {
            auxQ.offer(pushQ.poll());
            size--;
        }
        int ret = pushQ.poll();
        Queue<Integer> tmp = pushQ;
        pushQ = auxQ;
        auxQ = tmp;
        return ret;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return pushQ.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */