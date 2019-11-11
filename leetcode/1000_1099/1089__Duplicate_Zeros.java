class Solution {
    public void duplicateZeros(int[] arr) {
        int zeroCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeroCount++; 
            }
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 0) {
                zeroCount--;
            }
            int newIndex = i + zeroCount;
            if (newIndex < arr.length) {
                arr[newIndex] = arr[i];
                if (arr[i] == 0 && newIndex + 1 < arr.length) {
                    arr[newIndex + 1] = 0;
                }
            }
        }
    }
}

class Solution {
    public void duplicateZeros(int[] arr) {
        int[] zeroCount = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            zeroCount[i + 1] = zeroCount[i];
            if (arr[i] == 0) {
                zeroCount[i + 1]++; 
            }
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int newIndex = i + zeroCount[i];
            if (newIndex < arr.length) {
                arr[newIndex] = arr[i];
                if (arr[i] == 0 && newIndex + 1 < arr.length) {
                    arr[newIndex + 1] = 0;
                }
            }
        }
    }
}