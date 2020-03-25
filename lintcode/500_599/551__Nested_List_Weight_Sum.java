public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        int ret = 0;
        for (NestedInteger ni : nestedList) {
            ret += dfs(ni, 1);
        }
        return ret;
    }
    
    private int dfs(NestedInteger ni, int level) {
        if (ni.isInteger()) {
            return ni.getInteger() * level;
        }
        int ret = 0;
        for (NestedInteger child : ni.getList()) {
            ret += dfs(child, level + 1);
        }
        return ret;
    }
}