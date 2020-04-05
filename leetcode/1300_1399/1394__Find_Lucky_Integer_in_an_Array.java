class Solution {
    public int findLucky(int[] arr) {
        HashMap<Integer, Integer> vfMap = new HashMap<>();
        for (int num : arr) {
            vfMap.compute(num, (k,v) -> v == null ? 1 : v + 1);
        }
        int num = -1;
        int freq = 0;
        for (Map.Entry<Integer, Integer> entry : vfMap.entrySet()) {
            int v = entry.getKey();
            int f = entry.getValue();
            if (v == f) {
                if (f > freq || (f == freq && v > num)) {
                    num = v;
                    freq = f;
                }
            }
        }
        return num;
    }
}