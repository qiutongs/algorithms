class Solution {
    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }
        return helper(String.valueOf(n));
    }
    
    private int helper(String strN) {
        if (strN.length() == 1) {
            return strN.equals("0") ? 0 : 1;
        }
        char d1 = strN.charAt(0);
        
        // 1. first digit is [0, d1 - 1]
        // 1.1. all '1' in digit 2nd to digit n
        int ans1 = (d1 - '0') * total(strN.length() - 1);
        // 1.2 all '1' in digit 1st
        int ans2 = d1 > '1' ? (int)Math.pow(10, strN.length() - 1) : 0;
        
        // 2. first digit is d1
        // 2.1 all '1' in digit 2nd to digit n
        int ans3 = helper(strN.substring(1)); 
        // 2.2 all '1' in digit 1st
        int ans4 = d1 == '1' ? Integer.valueOf(strN.substring(1)) + 1 : 0;
        return ans1 + ans2 + ans3 + ans4;
    }
    
    private int total(int len) {
        return (int)(Math.pow(10, len - 1) * len);
    }
}