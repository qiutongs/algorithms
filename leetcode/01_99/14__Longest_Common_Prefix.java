class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while(isCommon(strs, idx)) {
            sb.append(strs[0].charAt(idx));
            idx++;
        }
        return sb.toString();
    }
    
    private boolean isCommon(String[] strs, int idx) {
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() <= idx) {
                return false;
            }
            if (i > 0 && strs[i].charAt(idx) != strs[i - 1].charAt(idx)) {
                return false;
            }
        }
        return true;
    }
}