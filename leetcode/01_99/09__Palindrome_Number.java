class Solution1 {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        return reverse(x) == x;
    }
    
    public long reverse(int x) {
        long ret = 0;
        while(x != 0) {
            ret = ret * 10 + x % 10;
            x /= 10;
        }
        return ret;
    }
}

class Solution2 {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        if (x % 10 == 0) {
            return false;
        }
        int xRight = 0;
        while(xRight < x) {
            xRight = xRight * 10 + x % 10;
            if (xRight == x) {
                return true;
            }
            x /= 10;   
            if (xRight == x) {
                return true;
            }
        }
        return false;
    }
}