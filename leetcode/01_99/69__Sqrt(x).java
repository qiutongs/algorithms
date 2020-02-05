// Binary Search
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

// Newton's method
class Solution {
    public int mySqrt(int x) {
        double xd = (double)x;
        double ret = (double)x;
        while(true) {
            double next = 0.5 * (ret + xd / ret);
            if ((int)next == (int)ret) {
                break;
            }
            ret = next;
        }
        return (int)ret;
    }
}