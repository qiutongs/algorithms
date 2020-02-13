// Insertion sort to maintain a sorted window
// Time: O(nk)
// Space: O(k)
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] ret = new double[nums.length - k + 1];
        int[] win = new int[k];
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= k) {
                remove(win, nums[i - k]);
                size--;
            }
            insert(win, size, nums[i]);
            size++;
            
            if (i >= k - 1) {
                ret[i - k + 1] = getMedian(win);
            }
        }
        return ret;
    }
    
    private void insert(int[] win, int size, int val) {
        int i = size - 1;
        for (; i >= 0; i--) {
            if (win[i] > val) {
                win[i + 1] = win[i];
            } else {
                break;
            }
        }
        win[i + 1] = val;
    }
    
    private void remove(int[] win, int val) {
        for (int i = 0; i < win.length; i++) {
            if (win[i] == val) {
                for (int j = i; j + 1 < win.length; j++) {
                    win[j] = win[j + 1];
                }
                break;
            }
        }
    }
    
    private double getMedian(int[] win) {
        int k = win.length;
        if (k % 2 == 0) {
            return ((long)win[k / 2 - 1] + (long)win[k / 2]) / 2.0;
        } else {
            return (double)win[k / 2];
        }
    }
}


// BST to maintain the window, manual move the median pointer
// Time: O(nlogk)
// Space: O(k)
// Ref: https://www.youtube.com/watch?v=kDS6ScZwNnI
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new double[]{};
        }
        double[] ret = new double[nums.length - k + 1];
        TreeSet<Data> win = new TreeSet<>();
        Data median = null;
        
        for (int i = 0; i < nums.length; i++) {
            if (i >= k) {
                median = remove(win, nums, i - k, median);
            }
            
            median = insert(win, nums, i, median);
            
            if (i >= k - 1) {
                ret[i - k + 1] = getMedian(win, median);
            }
        }
        return ret;
    }
    
    private Data insert(TreeSet<Data> win, int[] nums, int idx, Data median) {
        int size = win.size();
        Data newData = new Data(idx, nums[idx]);
        win.add(newData);
        
        if (median == null) {
            return newData;
        }
        
        if (size % 2 == 0 && newData.compareTo(median) > 0) {
            median = win.higher(median);
        } else if (size % 2 == 1 && newData.compareTo(median) < 0){
            median = win.lower(median);
        }
        return median;
    }
    
    private Data remove(TreeSet<Data> win, int[] nums, int idx, Data median) {
        int size = win.size();
        Data oldData = new Data(idx, nums[idx]);
        win.remove(oldData);
        if (size % 2 == 0 && oldData.compareTo(median) <= 0) {
            median = win.higher(median);
        } else if (size % 2 == 1 && oldData.compareTo(median) >= 0){
            median = win.lower(median);
        }
        return median;
    }
    
    private double getMedian(TreeSet<Data> win, Data median) {
        int k = win.size();
        if (k % 2 == 0) {
            return ((long)(median.val) + (long)(win.higher(median).val)) / 2.0;
        } else {
            return (double)median.val;
        }
    }
    
    private class Data implements Comparable<Data>{
        int idx;
        int val;
        Data(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
        public int compareTo(Data other) {
            if (this.val != other.val) {
                return this.val < other.val ? -1 : 1;
            }
            return this.idx - other.idx;
        }
        public boolean equals(Data other) {
            return this.idx == other.idx;
        }
    }
}