class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) {
            return Collections.emptyList();
        }
        
        Deque<Integer> ret = new LinkedList<>();
        
        int index = Arrays.binarySearch(arr, x);
        // Now index is at the one >= x
        index = index < 0 ? - index - 1 : index;
        
        int l = index - 1, r = index;
        // Because k <= arr.length, l and r cannot be both out of boundary
        for (int i = 0; i < k; i++) {
            if (r >= arr.length || (l >= 0 && x - arr[l] <= arr[r] - x)) {
                ret.addFirst(arr[l--]);
            } else {
                ret.addLast(arr[r++]);
            }
        }
        
        return (List<Integer>)ret;
    }
}

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) {
            return Collections.emptyList();
        }
        
        Deque<Integer> ret = new LinkedList<>();
        
        int index = binarySearch(arr, x);
        int l = index - 1, r = index;
        // Because k <= arr.length, l and r cannot be both out of boundary
        for (int i = 0; i < k; i++) {
            if (r >= arr.length || (l >= 0 && x - arr[l] <= arr[r] - x)) {
                ret.addFirst(arr[l--]);
            } else {
                ret.addLast(arr[r++]);
            }
        }
        
        return (List<Integer>)ret;
    }
    
    private int binarySearch(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (x < arr[mid]) {
                r = mid - 1;
            } else if (x > arr[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }
}