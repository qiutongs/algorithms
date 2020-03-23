class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ret = new ArrayList<>();
        dfs(S, 0, new ArrayList<>(), ret);
        return ret;
    }
    
    private boolean dfs(String S, int index, List<Integer> curList, List<Integer> ret) {
        if (index == S.length() && curList.size() >= 3) {
            ret.addAll(curList);
            return true;
        }
        for (int i = index; i < S.length(); i++) {
            if (i > index && S.charAt(index) == '0') {
                break;
            }
            long curLong = Long.valueOf(S.substring(index, i + 1));
            if (curLong > Integer.MAX_VALUE) {
                break;
            }
            int curNum = (int)curLong;
            
            boolean valid = false;
            if (curList.size() <= 1
             || curNum == curList.get(curList.size() - 1) + curList.get(curList.size() - 2)) {
                curList.add(curNum);
                if (dfs(S, i + 1, curList, ret)) {
                    return true;
                }
                curList.remove(curList.size() - 1);
            }
        }
        return false;
    }
}