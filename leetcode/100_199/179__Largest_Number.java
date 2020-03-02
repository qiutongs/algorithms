// Sort: careful handle prefix case
// Time: O(NlogN)
// Space: O(N)
class Solution {
    private Comparator<String> comp = new Comparator<>() {
        public int compare(String n1, String n2) {
            for (int i = 0; i < Math.min(n1.length(), n2.length()); i++) {
                if (n1.charAt(i) != n2.charAt(i)) {
                    return n2.charAt(i) - n1.charAt(i);
                }
            }
            if (n1.length() > n2.length()) {
                return compare(n1.substring(n2.length(), n1.length()), n2);
            } else if (n1.length() < n2.length()) {
                return compare(n1, n2.substring(n1.length(), n2.length()));
            } else {
                return 0;
            }
        }
    };
    
    public String largestNumber(int[] nums) {
        String[] numStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numStrs, comp);
        
        StringBuilder sb = new StringBuilder();
        for (String num : numStrs) {
            sb.append(num);
        }
        String ret = sb.toString();
        int i = 0;
        for (; i < ret.length() - 1; i++) {
            if (ret.charAt(i) != '0') {
                break;
            }
        }
        return ret.substring(i, ret.length());
    }
}