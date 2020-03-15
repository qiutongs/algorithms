// Bucket sort
class Solution {
    private int[][] workers;
    private int[][] bikes;
    
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        this.workers = workers;
        this.bikes = bikes;
        int N = workers.length, M = bikes.length;
        
        Pair[] pairs = new Pair[N * M];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                pairs[idx++] = new Pair(i, j);
            }
        }
        
        bucketSort(pairs);
        
        int[] ret = new int[N];
        Arrays.fill(ret, -1);
        boolean[] bikeUsed = new boolean[M];
        
        for (int i = 0; i < pairs.length; i++) {
            Pair pair = pairs[i];
            if (ret[pair.wIdx] == -1 && bikeUsed[pair.bIdx] == false) {
                ret[pair.wIdx] = pair.bIdx;
                bikeUsed[pair.bIdx] = true;
            }
        }
        return ret;
    }
    
    private void bucketSort(Pair[] pairs) {
        List<Pair>[] buckets = new List[2000];
        for (Pair pair : pairs) {
            if (buckets[pair.dis()] == null) {
                buckets[pair.dis()] = new ArrayList<>();
            }
            buckets[pair.dis()].add(pair);
        }
        int idx = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                Collections.sort(buckets[i]);
                for (Pair pair : buckets[i]) {
                    pairs[idx++] = pair;
                }
            }
        }
    }
    
    private class Pair implements Comparable<Pair> {
        int wIdx;
        int bIdx;
        Pair (int workerIdx, int bikeIdx) {
            this.wIdx = workerIdx;
            this.bIdx = bikeIdx;
        }
        public int compareTo(Pair other) {
            int diff = this.dis() - other.dis();
            if (diff != 0) {
                return diff;
            }
            if (this.wIdx != other.wIdx) {
                return this.wIdx - other.wIdx;
            }
            return this.bIdx - other.bIdx;
        }
        int dis() {
            return Math.abs(workers[wIdx][0] - bikes[bIdx][0]) + Math.abs(workers[wIdx][1] - bikes[bIdx][1]);
        }
    }
}

// Naive sort
class Solution {
    private int[][] workers;
    private int[][] bikes;
    
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        this.workers = workers;
        this.bikes = bikes;
        int N = workers.length, M = bikes.length;
        
        Pair[] pairs = new Pair[N * M];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                pairs[idx++] = new Pair(i, j);
            }
        }
        
        Arrays.sort(pairs);
        
        int[] ret = new int[N];
        Arrays.fill(ret, -1);
        boolean[] bikeUsed = new boolean[M];
        
        for (int i = 0; i < pairs.length; i++) {
            Pair pair = pairs[i];
            if (ret[pair.wIdx] == -1 && bikeUsed[pair.bIdx] == false) {
                ret[pair.wIdx] = pair.bIdx;
                bikeUsed[pair.bIdx] = true;
            }
        }
        return ret;
    }
    
    private class Pair implements Comparable<Pair> {
        int wIdx;
        int bIdx;
        Pair (int workerIdx, int bikeIdx) {
            this.wIdx = workerIdx;
            this.bIdx = bikeIdx;
        }
        public int compareTo(Pair other) {
            int diff = this.dis() - other.dis();
            if (diff != 0) {
                return diff;
            }
            if (this.wIdx != other.wIdx) {
                return this.wIdx - other.wIdx;
            }
            return this.bIdx - other.bIdx;
        }
        int dis() {
            return Math.abs(workers[wIdx][0] - bikes[bIdx][0]) + Math.abs(workers[wIdx][1] - bikes[bIdx][1]);
        }
    }
}