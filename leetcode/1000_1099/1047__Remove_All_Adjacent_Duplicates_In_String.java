class Solution {
    public String removeDuplicates(String S) {
        if (S == null || S.isEmpty()) {
            return S;
        }
        char[] ret = new char[S.length()];
        int j = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (j - 1 >= 0 && c == ret[j - 1]) {
                j--;
            } else {
                ret[j++] = c;
            }
        }
        return new String(ret, 0, j);
    }
}