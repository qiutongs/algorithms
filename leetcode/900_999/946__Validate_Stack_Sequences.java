// Stack simulation
// Time: O(N)
// Space: O(N)
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int popNum : popped) {
            if (stack.isEmpty() == false && stack.peek().intValue() == popNum) {
                stack.pop();
            } else {
                while(i < pushed.length && pushed[i] != popNum) {
                    stack.push(pushed[i]);
                    i++;
                }
                if (i == pushed.length) {
                    return false;
                } else {
                    i++;
                }
            }
        }
        return true;
    }
}