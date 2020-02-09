class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int ret = 0;
        HashMap<Character, Integer> cfMap = new HashMap<>();
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            char cR = s.charAt(r);
            cfMap.compute(cR, (k, v) -> v == null ? 1 : v + 1);
            
            while(l <= r && cfMap.get(cR) > 1) {
                char cL = s.charAt(l);
                cfMap.compute(cL, (k, v) -> v - 1);
                l++;
            }
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }
}