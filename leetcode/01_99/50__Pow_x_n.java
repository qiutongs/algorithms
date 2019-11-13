class Solution {
    public double myPow(double x, int n) {
        // avoid overflow when n is Integer.MIN_VALUE
        long nl = (long)n;
        if (nl < 0) {
            return 1.0 / pow(x, -nl);
        } else {
            return pow(x, nl);
        }
    }
    
    private double pow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double tmp = pow(x, n / 2);
        if (n % 2 == 0) {
            return tmp * tmp;
        } else {
            return tmp * tmp * x;
        }
    }
}

class Solution {
    public double myPow(double x, int n) {
        // avoid overflow when n is Integer.MIN_VALUE
        long nl = (long)n;
        if (nl < 0) {
            return 1.0 / pow(x, -nl);
        } else {
            return pow(x, nl);
        }
    }
    
    private double pow(double x, long n) {
        double ret = 1.0;
        while(n > 0) {
            if ((n & 1) == 1) {
                ret *= x;
            }
            x = x * x;
            n >>>= 1;
        }
        return ret;
    }
}