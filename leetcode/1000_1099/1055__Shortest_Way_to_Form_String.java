class Solution {
    public int shortestWay(String source, String target) {
        int[] ret = { Integer.MAX_VALUE };
        dfs(target, 0, source, 0, 0, ret);
        return ret[0] == Integer.MAX_VALUE ? -1 : ret[0];
    }
    
    private void dfs(String target, int tIdx, String source, int sIdx, int cut, int[] ret) {
        if (tIdx == target.length()) {
            ret[0] = Math.min(ret[0], cut);
            return;
        }
        int i = sIdx;
        while(i < source.length() && source.charAt(i) != target.charAt(tIdx)) {
            i++;
        }
        if (i < source.length()) {
            cut = sIdx == 0 ? cut + 1 : cut;
            sIdx = i == source.length() - 1 ? 0 : i + 1;
            dfs(target, tIdx + 1, source, sIdx, cut, ret);
        } else {
            i = 0;
            while(i < sIdx && source.charAt(i) != target.charAt(tIdx)) {
                i++;
            }
            if (i < sIdx) {
                dfs(target, tIdx + 1, source, i + 1, cut + 1, ret);
            }
        }
    }
}