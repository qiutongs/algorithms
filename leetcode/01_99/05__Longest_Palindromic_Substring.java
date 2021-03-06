// Native (start from center)
// Time O(n^2)
// Space O(1)
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int[] ret = {0, 0};
        for (int i = 0; i < s.length() - 1; i++) {
            getLongestPalin(s, i, i, ret);
            getLongestPalin(s, i, i + 1, ret);
        }
        return s.substring(ret[0], ret[1] + 1);
    }
    
    private void getLongestPalin(String s, int l, int r, int[] ret) {
        if (r >= s.length() || s.charAt(l) != s.charAt(r)) {
            return;
        }
        while(l - 1 >= 0 && r + 1 < s.length() && s.charAt(l - 1) == s.charAt(r + 1)) {
            l--;
            r++;
        }
        if (r - l > ret[1] - ret[0]) {
            ret[0] = l;
            ret[1] = r;
        }
    }
}


// Interval DP
// Time O(n^2)
// Space O(n^2)
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];
        int maxLeft = 0, maxRight = 0;
        
        // Iterate on small interval to big
        for (int interval = 1; interval <= n; interval++) {
            // Iterate on each starting point
            for (int start = 0; start + interval - 1 < n; start++) {
                int end = start + interval - 1;
                
                if (interval == 1) {
                    isPalin[start][end] = true;
                } else if (interval == 2) {
                    isPalin[start][end] = s.charAt(start) == s.charAt(end);
                } else {
                    isPalin[start][end] = isPalin[start + 1][end - 1] && s.charAt(start) == s.charAt(end);
                }
                
                if (isPalin[start][end] && end - start > maxRight - maxLeft) {
                    maxRight = end;
                    maxLeft = start;
                }
            }
        }
        
        return s.substring(maxLeft, maxRight + 1);
    }
}

/* 
* Classic optimal solution: manacher's algorithm

* Ref: https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
* https://www.youtube.com/watch?v=nbTSfrEfo6M
*/
class Solution3 {
    private static char DELI = '#';
    
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        String sDelimiter = preprocess(s);
        
        String longestPalin = manacher(sDelimiter);
        
        return postprocess(longestPalin);
    }
    
    private String manacher(String s) {
        int center = 0, right = 0;
        int[] maxPalin = new int[s.length()];
        
        Arrays.fill(maxPalin, 0);
        
        for (int i = 0; i < s.length(); i++) {
            int mirror = 2 * center - i;
            int l = i, r = i;
            
            // Leverage the symmetric property, no need to expand from center
            if (i <= right) {
                maxPalin[i] = Math.min(maxPalin[mirror], right - i);
                l = i - maxPalin[i];
                r = i + maxPalin[i];
            }
            
            // Expand
            while(l >= 0 && r < s.length()) {
                if (s.charAt(l) != s.charAt(r)) {
                    break;
                }
                
                maxPalin[i]++;
                l--;
                r++;
            }
            
            // Update center and right if the right exceeds
            if (r - 1 > right) {
               right = r - 1;
               center = i;
            }
        }
        
        // Find max palindrom
        int maxPalinLength = 0, maxPalinCenter = -1;
        
        for (int i = 0; i < maxPalin.length; i++) {
            if (maxPalin[i] > maxPalinLength) {
                maxPalinLength = maxPalin[i];
                maxPalinCenter = i;
            }
        }
        
        return s.substring(maxPalinCenter - maxPalinLength + 1, maxPalinCenter + maxPalinLength);
    }
    
    // Add delimiter "aba" -> "#a#b#a#"
    private String preprocess(String s) {
        char[] ret = new char[s.length() * 2 + 1];
        
        for (int i = 0; i < ret.length; i++) {
            if (i % 2 == 0) {
                ret[i] = DELI;
            } else {
                ret[i] = s.charAt(i / 2);
            }
        }
        
        return new String(ret);
    }
    
    // Remove delimiter "#a#b#a#" -> "aba"
    private String postprocess(String s) {
        char[] ret = new char[(s.length() - 1)/2];
        int j = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != DELI) {
               ret[j++] = s.charAt(i); 
            }
        }
        
        return new String(ret);
    }
}