// Iterator of Merged k lists with heap + sliding window
// Time: O(nlogk)
// Space: O(k)
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        if (nums.isEmpty()) {
            return new int[]{};
        }
        int[] ret = {0, Integer.MAX_VALUE};
        int K = nums.size();
        int[] groupFreqs = new int[K];
        int totalGroups = 0;
        
        MergedIterator l = new MergedIterator(nums);
        MergedIterator r = new MergedIterator(nums);
        while(r.hasNext()) {
            Data dataR = r.next();
            if (groupFreqs[dataR.group] == 0) {
                totalGroups++;
            }
            groupFreqs[dataR.group]++;
            
            while(totalGroups == K) {
                Data dataL = l.next();
                if (dataR.val - dataL.val < ret[1] - ret[0]) {
                    ret[0] = dataL.val;
                    ret[1] = dataR.val;
                }
                
                groupFreqs[dataL.group]--;
                if (groupFreqs[dataL.group] == 0) {
                    totalGroups--;
                }
            }
        }
        return ret;
    }
    
    private class MergedIterator {
        private List<List<Integer>> nums;
        private PriorityQueue<Data> minHeap = new PriorityQueue<>((d1, d2) -> d1.val - d2.val);
        private int[] indexes;
            
        MergedIterator(List<List<Integer>> nums) {
            this.nums = nums;
            indexes = new int[nums.size()];
            int i = 0;
            for (List<Integer> num : nums) {
                minHeap.offer(new Data(i++, num.get(0)));
            }
        }
        Data next() {
            Data minData = minHeap.poll();
            List<Integer> list = nums.get(minData.group);
            indexes[minData.group]++;
            if (indexes[minData.group] < list.size()) {
                minHeap.offer(new Data(minData.group, list.get(indexes[minData.group])));
            }
            return minData;
        }
        boolean hasNext() {
            return minHeap.isEmpty() == false;
        }
    }
    
    private class Data {
        int group;
        int val;
        Data(int group, int val) {
            this.group = group;
            this.val = val;
        }
        public String toString() {
            return String.valueOf(val);
        }
    }
}

// Merge k lists + sliding window
// Time: O(nlogk)
// Space: O(n)
// Out of memory: the total number of data is large
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        if (nums.isEmpty()) {
            return new int[]{};
        }
        List<Data> dataLists = merge(nums, 0, nums.size() - 1);

        int[] ret = {0, Integer.MAX_VALUE};
        int K = nums.size();
        int[] groupFreqs = new int[K];
        int totalGroups = 0;
        
        int l = 0;
        for (int r = 0; r < dataLists.size(); r++) {
            Data dataR = dataLists.get(r);
            if (groupFreqs[dataR.group] == 0) {
                totalGroups++;
            }
            groupFreqs[dataR.group]++;
            
            while(l <= r && totalGroups == K) {
                Data dataL = dataLists.get(l);
                if (dataR.val - dataL.val < ret[1] - ret[0]) {
                    ret[0] = dataL.val;
                    ret[1] = dataR.val;
                }
                
                groupFreqs[dataL.group]--;
                if (groupFreqs[dataL.group] == 0) {
                    totalGroups--;
                }
                l++;
            }
        }
        return ret;
    }
    
    List<Data> merge(List<List<Integer>> nums, int l, int r) {
        if (l == r) {
            List<Data> ret = new ArrayList<>(nums.get(l).size());
            for (Integer num : nums.get(l)) {
                ret.add(new Data(l, num));
            }
            return ret;
        }
        int mid = (l + r) / 2;
        List<Data> left = merge(nums, 0, mid);
        List<Data> right = merge(nums, mid + 1, r);
        return merge(left, right);
    }
    
    List<Data> merge(List<Data> list1, List<Data> list2) {
        List<Data> ret = new ArrayList<>(list1.size() + list2.size());
        int i = 0, j = 0;
        while(i < list1.size() && j < list2.size()) {
            if (list1.get(i).val < list2.get(j).val) {
                ret.add(list1.get(i));
                i++;
            } else {
                ret.add(list2.get(j));
                j++;
            }
        }
        while(i < list1.size()) {
            ret.add(list1.get(i));
            i++;
        }
        while(j < list2.size()) {
            ret.add(list2.get(j));
            j++;
        }
        return ret;
    }
    
    private class Data {
        int group;
        int val;
        Data(int group, int val) {
            this.group = group;
            this.val = val;
        }
        public String toString() {
            return String.valueOf(val);
        }
    }
}