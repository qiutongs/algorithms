class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if (queries == null || queries.length == 0) {
            return Collections.emptyList();
        }
        Boolean[] ret = new Boolean[queries.length];
        Arrays.fill(ret, false);
        if (pattern == null) {
            return Arrays.asList(ret);
        }
        
        String patUpper = getUpper(pattern);
        for (int i = 0; i < queries.length; i++) {
            if (patUpper.equals(getUpper(queries[i])) == false) {
                continue;
            }
            if (isSubsequence(queries[i], pattern)) {
                ret[i] = true;
            }
        }
        return Arrays.asList(ret);
    }
    
    private String getUpper(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    private boolean isSubsequence(String s, String p) {
        if (p.length() > s.length()) {
            return false;
        }
        if (p.isEmpty()) {
            return true;
        }
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == p.charAt(j)) {
                j++;
                if (j == p.length()) {
                    return true;
                }
            }
        }
        return false;
    }
    
}