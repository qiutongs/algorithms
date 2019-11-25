class Solution {
    public int[] countBits(int num) {
        int[] ret = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int tmp = i;
            while(tmp > 0) {
                ret[i]++;
                tmp = tmp & (tmp - 1);
            }
        }
        return ret;
    }
}

class Solution {
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        
        for (int i = 1;i < result.length;i++){
            result[i] = result[i>>1] + (i&1);
        }
        return result;
    }
}

class Solution {
    private static int[] LOOKUP = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};
    
    public int[] countBits(int num) {
        int[] ret = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            ret[i] = countBit(i);
        }
        return ret;
    }
    
    private int countBit(int num) {
        int ret = 0;
        while(num > 0) {
            ret += LOOKUP[num & 0x0F];
            num >>>= 4;
        }
        return ret;
    }
}

class Solution {
    public int[] countBits(int num) {
        int[] ret = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            ret[i] = countBit(i);
        }
        return ret;
    }
    
    private int countBit(int num) {
        num = ((num >>> 1) & 0x55555555) + (num & 0x55555555);
        num = ((num >>> 2) & 0x33333333) + (num & 0x33333333);
        num = ((num >>> 4) & 0x0F0F0F0F) + (num & 0x0F0F0F0F);
        num = ((num >>> 8) & 0x00FF00FF) + (num & 0x00FF00FF);
        num = ((num >>> 16) & 0x0000FFFF) + (num & 0x0000FFFF);
        return num;
    }
}