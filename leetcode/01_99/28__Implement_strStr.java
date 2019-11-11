public class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.isEmpty()) {
            return 0;
        }
        for (int i = 0; i + needle.length() <= haystack.length(); i++) {
            int j = 0;
            for (; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}

// Rabin Karp
class Solution {
    private final int BASE = 31;
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.isEmpty()) {
            return 0;
        }
        
        int m = haystack.length(), n = needle.length();
        long needleHash = hashCode(needle, n);
        long hayHash = 0;
        long coeff = (long)Math.pow((double)BASE, (double)n - 1);
        
        for (int i = 0; i + n <= m; i++) {
            if (i == 0) {
                hayHash = hashCode(haystack, n);
            } else {
                hayHash = hayHash - coeff * (int)haystack.charAt(i - 1);
                hayHash = hayHash * BASE + (int)haystack.charAt(i + n - 1);
            }
            if (hayHash == needleHash && isMatch(haystack, i, needle)) {
                return i;
            }
        }
        return -1;
    }
    
    // cannot tolerate overflow
    private long hashCode(String s, int length) {
        long ret = 0;
        for (int i = 0; i < length; i++) {
            ret = ret * BASE + (int)s.charAt(i);
        }
        return ret;
    }
    
    private boolean isMatch(String haystack, int start, String needle) {
        for (int i = 0; i < needle.length(); i++) {
            if (haystack.charAt(start + i) != needle.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}