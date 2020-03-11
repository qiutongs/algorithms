public class Solution {
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: An integer
     */
    public int bitSwapRequired(int a, int b) {
        int diff = a ^ b;
        int ret = 0;
        while(diff != 0) {
            ret++;
            diff &= (diff - 1);
        }
        return ret;
    }
}