// Use add and substract only
// Time: O(dividend)
// Time Limit Exceeded: -2147483648 / 2
class Solution {
    public int divide(int dividend, int divisor) {
        boolean positive = (dividend >= 0 && divisor > 0) || (dividend <= 0 && divisor < 0);
        
        long dividendL = (long)dividend, divisorL = (long)divisor;
        dividendL = dividendL < 0 ? -dividendL : dividendL;
        divisorL = divisorL < 0 ? -divisorL : divisorL;

        long ret = 0;
        while(dividendL >= divisorL) {
            dividendL -= divisorL;
            ret++;
        }
        ret = positive ? ret : -ret;
        return ret > Integer.MAX_VALUE ? Integer.MAX_VALUE : ret < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int)ret;
    }
}

// Subtract 2^n * divisor at a time
// Time: O(log(dividend))
// Ref: https://www.youtube.com/watch?v=uD1Cw1JbD8E
class Solution {
    public int divide(int dividend, int divisor) {
        boolean positive = (dividend >= 0 && divisor > 0) || (dividend <= 0 && divisor < 0);
        
        long dividendL = (long)dividend, divisorL = (long)divisor;
        dividendL = dividendL < 0 ? -dividendL : dividendL;
        divisorL = divisorL < 0 ? -divisorL : divisorL;

        long ret = 0;
        while(dividendL >= divisorL) {
            int i = 0;
            while((divisorL << (i + 1)) <= dividendL) {
                i++;
            }
            ret += 1L << i;
            dividendL -= divisorL << i;
        }
        ret = positive ? ret : -ret;
        return ret > Integer.MAX_VALUE ? Integer.MAX_VALUE : ret < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int)ret;
    }
}