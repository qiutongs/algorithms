class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        while(fast != 1 && next(fast) != 1) {
            slow = next(slow);
            fast = next(next(fast));
            
            if (slow == fast) {
                return false;
            } 
        }
        return true;
    }
    
    private int next(int n) {
        int ret = 0;
        while(n > 0) {
            ret += (n % 10) * (n % 10);
            n /= 10;
        }
        return ret;
    }
}