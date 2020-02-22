// Sort + binary search
// Time: O(M * L + (N * L + M)logM)
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] fWords = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            fWords[i] = f(words[i]);
        }
        Arrays.sort(fWords);
        
        int[] ret = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ret[i] = words.length - binarySeach(fWords, f(queries[i]));
        }
        return ret;
    }
    
    private int binarySeach(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] > val) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    
    private int f(String s) {
        int ret = 1;
        char min = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) < min) {
                min = s.charAt(i);
                ret = 1;
            } else if (s.charAt(i) == min) {
                ret++;
            }
        }
        return ret;
    }
}