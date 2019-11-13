class Solution {
    public int mySqrt(int x) {
        int l = 0, r = x;
        while(l <= r) {
            int mid = (l + r) / 2;
            if ((long)mid * (long)mid <= x) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
}

class Solution {
    public int mySqrt(int x) {
        double a = (double)x;
        double x1 = a;
        double tmp = x1;
        do {
            tmp = x1;
            x1 = 0.5 * (x1 + a / x1);
        } while((int)(tmp) > (int)(x1));
        return (int)x1;
    }
}