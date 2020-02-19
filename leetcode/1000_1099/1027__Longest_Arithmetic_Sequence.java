// DFS + memo
// Time: O(N^N)
// Space: O(N)
// Note: Time Limit Exceeded. Not understand why this worse than raw DFS
class Solution {
    public int longestArithSeqLength(int[] A) {
        HashMap<Integer, Integer>[] memo = new HashMap[A.length + 1];
        for (int i = 0 ; i < memo.length; i++) {
            memo[i] = new HashMap<>();
        }
        return dfs(A, 0, null, memo);
    }
    
    private int dfs(int[] A, int nbOffset, Integer diff, HashMap<Integer, Integer>[] memo) {
        if (diff != null && memo[nbOffset].containsKey(diff)) {
            return memo[nbOffset].get(diff);
        }
        Integer cur = nbOffset == 0 ? null : A[nbOffset - 1];
        
        int ret = 0;
        for (int i = nbOffset; i < A.length; i++) {
            if (cur == null && diff == null) {
                ret = Math.max(ret, 1 + dfs(A, i + 1, null, memo));
            } else if (cur != null && diff == null) {
                ret = Math.max(ret, 1 + dfs(A, i + 1, A[i] - cur, memo));
            } else {
                if (A[i] - cur == diff) {
                    ret = Math.max(ret, 1 + dfs(A, i + 1, diff, memo));
                }
            }
        }
        if (diff != null) {
            memo[nbOffset].put(diff, ret);
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