class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        StringBuilder sb = new StringBuilder();
        String subret = countAndSay(n - 1);
        char c = subret.charAt(0);
        int count = 1;
        int i = 1;
        while(i < subret.length()) {
            if (subret.charAt(i) == c) {
                count++;
            } else {
                sb.append(count);
                sb.append(c);
                count = 1;
                c = subret.charAt(i);
            }
            i++;
        }
        sb.append(count);
        sb.append(c);
        return sb.toString();
    }
}