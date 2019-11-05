class Solution {
    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        
        // 1. trim whitespaces
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        
        // 2. check sign and remove it
        char initial = str.charAt(0);
        boolean positive = initial == '-' ? false : true;
        str = initial == '-' || initial == '+' ? str.substring(1) : str;
        
        // 3. extract valid substring
        int i = 0;
        while(i < str.length() && Character.isDigit(str.charAt(i))) {
            i++;
        }
        str = str.substring(0, i);
        if (str.length() == 0) {
            return 0;
        }
        
        // 4. remove redundant 0
        i = 0;
        while(i < str.length() && str.charAt(i) == '0') {
            i++;
        }
        str = str.substring(i);

        // 5. check out of range
        if (str.length() > 10) {
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE ;
        }

        // 6. convert to long
        long val = 0;
        for (int j = 0; j < str.length(); j++) {
            val = val * 10 + (long)(str.charAt(j) - '0');
        }
        val = positive ? val : -val;
        
        return val > Integer.MAX_VALUE ? Integer.MAX_VALUE : val < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int)val;
    }
}