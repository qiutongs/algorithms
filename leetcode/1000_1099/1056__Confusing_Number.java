class Solution {
    public boolean confusingNumber(int N) {
        int rotateN = 0;
        int copyN = N;
        while(N > 0) {
            int d = N % 10;
            if (isValid(d) == false) {
                return false;
            }
            rotateN = rotateN * 10 + flip(d);
            
            N /= 10;
        }
        return copyN != rotateN;
    }
    
    private int flip(int digit) {
        return digit == 6 ? 9 
             : digit == 9 ? 6 
             : digit;
    }
    
    private boolean isValid(int digit) {
        return digit == 0 
            || digit == 1
            || digit == 6
            || digit == 8
            || digit == 9;
    }
}