/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public NestedInteger deserialize(String s) {
        int[] offset = { 0 };
        NestedInteger ret = new NestedInteger();
        dfs(s, offset, ret);
        return ret.getList().get(0);
    }
    
    private void dfs(String s, int[] offset, NestedInteger container) {
        Integer num = null;
        int sign = 1;
        for (; offset[0] < s.length(); offset[0]++) {
            char c = s.charAt(offset[0]);
            if (c == '[') {
                offset[0]++;
                NestedInteger child = new NestedInteger();
                container.add(child);
                dfs(s, offset, child);
            } else if (c == ']') {
                break;
            } else if (Character.isDigit(c)) {
                num = num == null ? 0 : num;
                num = num * 10 + (int)(c - '0');
            } else if (c == '-') {
                sign = -1;
            } else if (c == ',') {
                if (num != null) {
                    container.add(new NestedInteger(sign * num));
                    num = null;
                    sign = 1;
                }
            }
        }
        
        if (num != null) {
            container.add(new NestedInteger(sign * num));
        }
    }
}