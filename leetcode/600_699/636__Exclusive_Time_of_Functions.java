// Stack to simulate call stack
// Time: O(N)
// Space: O(N)
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ret = new int[n];
        Stack<int[]> stack = new Stack<>();
        
        for (String log : logs) {
            String[] strs = log.split(":");
            int[] func = {Integer.valueOf(strs[0]), Integer.valueOf(strs[2])};
            
            if (strs[1].equals("start")) {
                if (stack.isEmpty() == false) {
                    ret[stack.peek()[0]] += func[1] - stack.peek()[1];
                }
                stack.push(func);
            } else {
                int[] cur = stack.pop();
                ret[cur[0]] += func[1] - cur[1] + 1;
                if (stack.isEmpty() == false) {
                    stack.peek()[1] = func[1] + 1;
                }
            }
        }
        return ret;
    }
}