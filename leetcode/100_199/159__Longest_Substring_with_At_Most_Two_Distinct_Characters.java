
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        HashMap<Character, Integer> vfMap = new HashMap<>();
        int ret = 0;
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            char cR = s.charAt(r);
            vfMap.compute(cR, (k, v) -> v == null ? 1 : v + 1);
            while(l <= r && vfMap.size() > 2) {
                char cL = s.charAt(l);
                vfMap.compute(cL, (k, v) -> v - 1);
                if (vfMap.get(cL).intValue() == 0) {
                    vfMap.remove(cL);
                }
                l++;
            }
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }
}