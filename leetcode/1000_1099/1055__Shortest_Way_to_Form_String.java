class Solution {
    public int shortestWay(String source, String target) {
        return dfs(target, 0, source, 0);
    }
    
    private int dfs(String target, int tIdx, String source, int sIdx) {
        if (tIdx == target.length()) {
            return 0;
        }
        sIdx = sIdx == source.length() ? 0 : sIdx;
        int initial = sIdx == 0 ? 1 : 0;

        int i = sIdx;
        while(i < source.length() && source.charAt(i) != target.charAt(tIdx)) {
            i++;
        }
        if (i < source.length()) {
            int subret = dfs(target, tIdx + 1, source, i + 1);
            return subret == -1 ? -1 : initial + subret;
        } 
        
        i = 0;
        while(i < sIdx && source.charAt(i) != target.charAt(tIdx)) {
            i++;
        }
        if (i < sIdx) {
            int subret = dfs(target, tIdx + 1, source, i + 1);
            return subret == -1 ? -1 : initial + 1 + subret;
        }
        return -1;
    }
}