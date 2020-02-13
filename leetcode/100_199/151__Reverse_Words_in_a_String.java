class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        char[] cArr = s.toCharArray();
        reverse(cArr, 0, cArr.length - 1); 
        int start = 0;
        for (int i = 0; i <= cArr.length; i++) {
            if (i == cArr.length || cArr[i] == ' ') {
                reverse(cArr, start, i - 1);
                start = i + 1;
            }
        }
        return new String(cArr);
    }
    
    private void reverse(char[] cArr, int l, int r) {
        while(l < r) {
            char tmp = cArr[l];
            cArr[l] = cArr[r];
            cArr[r] = tmp;
            l++;
            r--;
        }
    }
}