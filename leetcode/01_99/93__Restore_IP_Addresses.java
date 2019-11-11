class Solution {
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4) {
            return Collections.emptyList();
        }
        List<String> ret = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(String s, int offset, List<String> curList, List<String> ret) {
        if (offset == s.length() && curList.size() == 4) {
            ret.add(toString(curList));
            return;
        }
        if (curList.size() == 4) {
            return;
        }
        for (int i = offset; i < offset + 3 && i < s.length(); i++) {
            String ip = s.substring(offset, i + 1);
            if (isValidIp(ip)) {
                curList.add(ip);
                dfs(s, i + 1, curList, ret);
                curList.remove(curList.size() - 1);
            }
        }
    }
    
    private boolean isValidIp(String s) {
        if (s.charAt(0) == '0') {
            return s.length() == 1 ? true : false;
        }
        Integer intValue = Integer.valueOf(s);
        return intValue >= 0 && intValue <= 255;
    }
    
    private String toString(List<String> curList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < curList.size(); i++) {
            sb.append(curList.get(i));
            if (i < curList.size() - 1) {
                sb.append('.');
            }
        }
        return sb.toString();
    }
}