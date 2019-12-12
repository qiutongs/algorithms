class Solution {
    public boolean isValid(String S) {
        if (S == null || S.isEmpty()) {
            return false;
        }
        char[] charArr = new char[S.length()];
        int j = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (j - 2 >= 0 && charArr[j - 2] == 'a' && charArr[j - 1] == 'b' && c == 'c') {
                j = j - 2;
            } else {
                charArr[j++] = c;
            }
        }
        return j == 0;
    }
}