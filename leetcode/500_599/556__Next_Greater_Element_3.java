class Solution {
    public int nextGreaterElement(int n) {
        int[] digits = toDigits(n);
        
        int smallIdx = digits.length - 2;
        for (; smallIdx >= 0; smallIdx--) {
            if (digits[smallIdx] < digits[smallIdx + 1]) {
                break;
            }
        }
        
        if (smallIdx >= 0) {
            int largetIdx = digits.length - 1;
            for (; largetIdx > smallIdx; largetIdx--) {
                if (digits[largetIdx] > digits[smallIdx]) {
                    break;
                }
            }
            
            swap(digits, smallIdx, largetIdx);
            reverse(digits, smallIdx + 1, digits.length - 1);
            long ret = toLong(digits);
            return ret > Integer.MAX_VALUE ? -1 : (int)ret;
        } else {
            return -1;
        }
    }
    
    private void reverse(int[] arr, int l, int r) {
        while(l < r) {
            swap(arr, l, r);
            l++;
            r--;
        }
    }
    
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    private int[] toDigits(int n) {
        List<Integer> digits = new ArrayList<>();
        while(n > 0) {
            digits.add(n % 10);
            n /= 10;
        }
        Collections.reverse(digits);
        
        int[] ret = new int[digits.size()];
        int i = 0;
        for (Integer digit : digits) {
            ret[i++] = digit;
        }
        return ret;
    }
    
    private long toLong(int[] digits) {
        long ret = 0;
        for (int i = 0; i < digits.length; i++){
            ret = ret * 10 + digits[i];
        }
        return ret;
    }
}