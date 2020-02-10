class Solution {
    private static final String[] PHONE_DIC = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        List<String> ret = new LinkedList<>();
        dfs(digits, 0, new StringBuilder(), ret);
        return ret;
    }

    private void dfs(String digits, int offset, StringBuilder curStr, List<String> ret) {
        if (offset == digits.length()) {
            ret.add(curStr.toString());
            return;
        }
        String letters = PHONE_DIC[((int)(digits.charAt(offset) - '2'))];
        for (int i = 0; i < letters.length(); i++) {
            curStr.append(letters.charAt(i));
            dfs(digits, offset + 1, curStr, ret);
            curStr.deleteCharAt(curStr.length() - 1);
        }
    }
}
