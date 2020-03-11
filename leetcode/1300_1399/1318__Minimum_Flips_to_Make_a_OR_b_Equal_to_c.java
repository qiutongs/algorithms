class Solution {
    public int minFlips(int a, int b, int c) {
        int diffA = a ^ c;
        int diffB = b ^ c;
        int diffAandB = diffA & diffB;
        return bitCount(c & diffAandB) + bitCount(~c & diffA) + bitCount(~c & diffB);
    }
    
    private int bitCount(int n) {
        int ret = 0;
        while(n != 0) {
            ret++;
            n &= n - 1;
        }
        return ret;
    }
}