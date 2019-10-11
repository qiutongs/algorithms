// stackoverflow
class Solution1 {
    public double myPow(double x, int n) {
        if (n >= 0) {
            return helper(x, n);
        } else {
            return 1.0 / helper(x, -n);
        }
    }
    
    private double helper(double x, int n) {
        if (n == 0) {
            return 1;
        }
        
        return helper(x, n - 1) * x;
    }
}

// Time limit exceed
class Solution2 {
    public double myPow(double x, int n) {
        double ret = 1.0;
        int absN = Math.abs(n);
        
        while(absN > 0) {
            ret *= x;
            absN--;
        }
        
        if (n >= 0) {
            return ret;
        } else {
            return 1.0 / ret;
        }
    }
}

// pass: binary search
class Solution {
    public double myPow(double x, int n) {
        if (n >= 0) {
            return helper(x, n);
        } else {
            return 1.0 / helper(x, -n);
        }
    }
    
    private double helper(double x, int n) {
        if (n == 0) {
            return 1;
        }
        
        double squrRoot = helper(x, n / 2);
        
        if (n % 2 == 0) {
            return squrRoot * squrRoot;
        } else {
            return squrRoot * squrRoot * x;
        }
    }
}