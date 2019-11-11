class Solution {
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }
        List<List<String>> ret = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(String s, int offset, List<String> curList, List<List<String>> ret) {
        if (offset == s.length()) {
            ret.add(new ArrayList<>(curList));
            return;
        }
        
        for (int i = offset; i < s.length(); i++) {
            if (isPalindrome(s, offset, i)) {
                curList.add(s.substring(offset, i + 1));
                dfs(s, i + 1, curList, ret);
                curList.remove(curList.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}