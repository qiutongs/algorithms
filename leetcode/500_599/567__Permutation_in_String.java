class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }
        
        int[] cfArr = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            cfArr[toInt(s1.charAt(i))]++;
        }
        
        int target = s1.length();
        for (int r = 0; r < s2.length(); r++) {
            char cR = s2.charAt(r);
            if (cfArr[toInt(cR)] > 0) {
                target--;
            }
            cfArr[toInt(cR)]--;
            
            if (r >= s1.length()) {
                char cL = s2.charAt(r - s1.length());
                if (cfArr[toInt(cL)] >= 0) {
                    target++;
                }
                cfArr[toInt(cL)]++;
            }
            
            if (target == 0) {
               return true;
            }
        }
        return false;
    }
    
    private int toInt(char c) {
        return (int)(c - 'a');
    }
}