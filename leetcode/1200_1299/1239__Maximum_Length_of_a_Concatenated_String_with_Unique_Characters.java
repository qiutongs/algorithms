class Solution {
    public int maxLength(List<String> arr) {
        List<UniString> uniList = new ArrayList<>();
        for (String str : arr) {
            uniList.add(new UniString(str));
        }
        int[] ret = {0};
        dfs(uniList, 0, new UniString(""), ret);
        return ret[0];
    }
    
    private void dfs(List<UniString> uniList, int index, UniString curStr, int[] ret) {
        ret[0] = Math.max(ret[0], curStr.length);
        
        for (int i = index; i < uniList.size(); i++) {
            if (uniList.get(i).unique) {
                UniString u = curStr.union(uniList.get(i));
                if (u != null) {
                    dfs(uniList, i + 1, u, ret);
                }
            }
        }
    }
    
    private class UniString {
        boolean[] chars = new boolean[26];
        boolean unique = true;
        int length = 0;
        
        UniString(String s) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (chars[c - 'a']) {
                    unique = false;
                    return;
                }
                chars[c - 'a'] = true;
                length++;
            }
        }
        
        private UniString union(UniString other) {
            UniString ret = new UniString("");
            for (int i = 0; i < 26; i++) {
                if (chars[i] && other.chars[i]) {
                    return null;
                }
                ret.chars[i] = chars[i] || other.chars[i];
                if (ret.chars[i]) {
                    ret.length++;
                }
            }
            return ret;
        }
    }
}