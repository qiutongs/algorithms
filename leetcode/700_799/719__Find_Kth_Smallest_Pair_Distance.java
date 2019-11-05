// Correct, but Time limit exceed with large input. nlogn is not good enough
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        
        PriorityQueue<Pair> heap = new PriorityQueue<>((p1, p2) -> p1.d - p2.d);
        for (int i = 1; i < nums.length; i++) {
            heap.add(new Pair(i - 1, i, nums[i] - nums[i - 1]));
        }
        
        int ret = -1;
        for (int i = 0; i < k; i++) {
            Pair min = heap.remove();
            ret = min.d;
            if (min.j + 1 < nums.length) {
                heap.add(new Pair(min.i, min.j + 1, nums[min.j + 1] - nums[min.i]));
            }
        }
        return ret;
    }
    
    private class Pair {
        int i;
        int j;
        int d;
        Pair(int i, int j, int d) {
            this.i = i;
            this.j = j;
            this.d = d;
        }
    }
}

// Greedy: WRONG!
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        
        int d = 1;
        while(k > n - d) {
            k -= n - d;
            d++;
        }
        
        int[] dis = new int[nums.length - d];
        for (int i = 0; i + d < nums.length; i++) {
            dis[i] = nums[i + d] - nums[i];
        }
        Arrays.sort(dis);
        return dis[k - 1];
    }
}