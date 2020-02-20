// DFS + memo
// Time: O(N^N)
// Space: O(N)
// Note: Time Limit Exceeded. Not understand why this worse than raw DFS
class Solution {
    public int longestArithSeqLength(int[] A) {
        HashMap<Integer, Integer>[] memo = new HashMap[A.length];
        for (int i = 0 ; i < memo.length; i++) {
            memo[i] = new HashMap<>();
        }
        int ret = 0;
        for (int i = 0; i < A.length; i++) {
            ret = Math.max(ret, dfs(A, i, null, memo));
        }
        return ret;
    }
    
    private int dfs(int[] A, int curIndex, Integer diff, HashMap<Integer, Integer>[] memo) {
        if (diff != null && memo[curIndex].containsKey(diff)) {
            return memo[curIndex].get(diff);
        }
        
        int subret = 0;
        for (int i = curIndex + 1; i < A.length; i++) {
            if (diff == null) {
                subret = Math.max(subret, dfs(A, i, A[i] - A[curIndex], memo));
            } else {
                if (A[i] - A[curIndex] == diff.intValue()) {
                    subret = Math.max(subret, dfs(A, i, diff, memo));
                }
            }
        }
        int ret = subret + 1;
        if (diff != null) {
            memo[curIndex].put(diff, ret);
        }
        return ret;
    }
}

// raw DFS
class Solution {
    public int longestArithSeqLength(int[] A) {
        return dfs(A, 0, null);
    }
    
    private int dfs(int[] A, int offset, Integer diff) {
        if (offset == A.length) {
            return 0;
        }
        int ret = 0;
        Integer cur = offset == 0 ? null : A[offset - 1];
        for (int i = offset; i < A.length; i++) {
            if (cur == null && diff == null) {
                ret = Math.max(ret, 1 + dfs(A, i + 1, null));
            } else if (cur != null && diff == null) {
                ret = Math.max(ret, 1 + dfs(A, i + 1, A[i] - cur));
            } else {
                if (A[i] - cur == diff) {
                    ret = Math.max(ret, 1 + dfs(A, i + 1, diff));
                }
            }
        }
        return ret;
    }
}