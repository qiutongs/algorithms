// https://leetcode.com/problems/expression-add-operators/discuss/71895/Java-Standard-Backtrace-AC-Solutoin-short-and-clear
class Solution {
    public List<String> addOperators(String num, int target) {
        if (num == null || num.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> ret = new ArrayList<>();
        dfs(num, 0, target, new ArrayList<>(), 0, 0, ret);
        return ret;
    }
    
    private void dfs(String digits, int offset, int target, List<String> curExps, long curVal, long curMul, List<String> ret) {
        if (offset == digits.length()) {
            if (curVal == target) {
                ret.add(output(curExps));
            }
            return;
        }
        for (int i = offset; i < digits.length(); i++) {
            if (digits.charAt(offset) == '0' && i > offset) {
                break;
            }
            String curNumStr = digits.substring(offset, i + 1);
            long curNum = Long.valueOf(curNumStr);
            if (offset == 0) {
                curExps.add(curNumStr);
                dfs(digits, i + 1, target, curExps, curNum, curNum, ret);
                curExps.remove(curExps.size() - 1);
            } else {
                curExps.add("+" + curNumStr);
                dfs(digits, i + 1, target, curExps, curVal + curNum, curNum, ret);
                curExps.remove(curExps.size() - 1);
                
                curExps.add("-" + curNumStr);
                dfs(digits, i + 1, target, curExps, curVal - curNum, -curNum, ret);
                curExps.remove(curExps.size() - 1);
                
                curExps.add("*" + curNumStr);
                dfs(digits, i + 1, target, curExps, curVal - curMul + curMul * curNum, curMul * curNum, ret);
                curExps.remove(curExps.size() - 1);
            }
        }
    }
    
    private String output(List<String> curStrs) {
        StringBuilder sb = new StringBuilder();
        for (String str : curStrs) {
            sb.append(str);
        }
        return sb.toString();
    }
}