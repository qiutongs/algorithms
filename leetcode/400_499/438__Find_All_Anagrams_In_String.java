class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null || s.length() < p.length()) {
            return Collections.emptyList();
        }
        int[] cfP = new int[26];
        for (int i = 0; i < p.length(); i++) {
            cfP[toInt(p.charAt(i))]++;
        }
        
        List<Integer> ret = new ArrayList<>();
        int target = p.length();

        for (int r = 0; r < s.length(); r++) {
            char cR = s.charAt(r);
            if (cfP[toInt(cR)] > 0) {
                target--;
            }
            cfP[toInt(cR)]--;
            
            if (r >= p.length()) {
                char cL = s.charAt(r - p.length());
                if (cfP[toInt(cL)] >= 0) {
                    target++;
                }
                cfP[toInt(cL)]++;
            }
            
            if (target == 0) {
               ret.add(r - p.length() + 1);
            }
        }
        return ret;
    }
    
    private int toInt(char c) {
        return (int)(c - 'a');
    }
}