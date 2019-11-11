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

class Solution {
    private static final HashMap<Character, String> PHONE_DIC = new HashMap<>();
    static {
        PHONE_DIC.put('2', "abc");
        PHONE_DIC.put('3', "def");
        PHONE_DIC.put('4', "ghi");
        PHONE_DIC.put('5', "jkl");
        PHONE_DIC.put('6', "mno");
        PHONE_DIC.put('7', "pqrs");
        PHONE_DIC.put('8', "tuv");
        PHONE_DIC.put('9', "wxyz");
    }
    
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()){
            return Collections.emptyList();
        }
        
        List<String> result = new LinkedList<>();
        char firstDigit = digits.charAt(0);
        char[] firstDigitCArray = PHONE_DIC.get(firstDigit).toCharArray();
        
        if (digits.length() == 1){
            for (char c : firstDigitCArray){
                result.add(String.valueOf(c));
            }
            return result;
        }
        
        List<String> subResults = letterCombinations(digits.substring(1));
        for (char c : firstDigitCArray){
            for (String subResult : subResults){
                result.add(c + subResult);
            }
        }
        
        
        return result;
    }
}
