class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        
        HashMap<Character, Integer> vfMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            vfMap.compute(t.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }
        
        int minL = 0, minR = Integer.MAX_VALUE;
        int target = t.length();

        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            char cR = s.charAt(r);
            Integer fR = vfMap.get(cR);
            if (fR != null) {
                if (fR > 0) {
                    target--;
                }
                vfMap.put(cR, fR - 1);
            }
            
            while(target == 0) {
                if (r - l < minR - minL) {
                    minR = r;
                    minL = l;
                }
                char cL = s.charAt(l);
                Integer fL = vfMap.get(cL);
                if (fL != null) {
                    if (fL == 0) {
                        target++;
                    }
                    vfMap.put(cL, fL + 1);
                }
                l++;
            }
        }
        return minR == Integer.MAX_VALUE ? "" : s.substring(minL, minR + 1);
    }
}