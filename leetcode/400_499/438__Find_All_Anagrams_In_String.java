class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null || s.length() < p.length()) {
            return Collections.emptyList();
        }
        int[] reqFreqs = new int[26];
        int reqTotal = p.length();
        for (int i = 0; i < p.length(); i++) {
            reqFreqs[toIndex(p.charAt(i))]++;
        }
        
        List<Integer> ret = new ArrayList<>();
        for (int r = 0; r < s.length(); r++) {
            char cR = s.charAt(r);
            if (reqFreqs[toIndex(cR)] > 0) {
                reqTotal--;
            }
            reqFreqs[toIndex(cR)]--;
            
            if (r >= p.length()) {
                char cL = s.charAt(r - p.length());
                if (reqFreqs[toIndex(cL)] >= 0) {
                    reqTotal++;
                }
                reqFreqs[toIndex(cL)]++;
            }
            
            if (reqTotal == 0) {
               ret.add(r - p.length() + 1);
            }
        }
        return ret;
    }
    
    private int toIndex(char c) {
        return (int)(c - 'a');
    }
}