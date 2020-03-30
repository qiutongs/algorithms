// Math: store the index of max element on the right of each element
class Solution {
    public int maximumSwap(int num) {
        if (num < 10) {
            return num;
        }
        char[] numArr = String.valueOf(num).toCharArray();
        int[] numMaxIdx = new int[numArr.length];
        Arrays.fill(numMaxIdx, -1);
        
        int curMaxIdx = numArr.length - 1;
        for (int i = numArr.length - 2; i >= 0; i--) {
            if (numArr[curMaxIdx] > numArr[i]) {
                numMaxIdx[i] = curMaxIdx;
            } else if (numArr[curMaxIdx] < numArr[i]) {
                curMaxIdx = i;
            }
        }
        
        for (int i = 0; i < numMaxIdx.length; i++) {
            if (numMaxIdx[i] != -1) {
                char tmp = numArr[i];
                numArr[i] = numArr[numMaxIdx[i]];
                numArr[numMaxIdx[i]] = tmp;
                return Integer.valueOf(new String(numArr));
            }
        }
        return num;
    }
}