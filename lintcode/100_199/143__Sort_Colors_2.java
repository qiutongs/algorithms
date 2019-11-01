public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        helper(colors, 0, colors.length - 1, 1, k);
    }
    
    private void helper(int[] colors, int l, int r, int kl, int kr) {
        if (kl == kr) {
            return;
        }
        
        int kMid = (kl + kr) / 2;
        int j = l;
        for (int i = l; i <= r; i++) {
            if (colors[i] <= kMid) {
                int tmp = colors[j];
                colors[j++] = colors[i];
                colors[i] = tmp;
            }
        }
        
        helper(colors, l, j - 1, kl, kMid);
        helper(colors, j, r, kMid + 1, kr);
    }
}