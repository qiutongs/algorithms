class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        HashMap<Character, Integer> vfMap = new HashMap<>();
        int ret = 0;
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            vfMap.compute(s.charAt(r), (c, v) -> v == null ? 1 : v + 1);
            while(l <= r && vfMap.size() > k) {
                vfMap.compute(s.charAt(l), (c, v) -> v - 1);
                if (vfMap.get(s.charAt(l)).intValue() == 0) {
                    vfMap.remove(s.charAt(l));
                }
                l++;
            }
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }
}