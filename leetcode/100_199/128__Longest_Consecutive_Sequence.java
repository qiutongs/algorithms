class Solution {
    public int longestConsecutive(int[] nums) {
        int ret = 0;
        
        HashMap<Integer, Boolean> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, true);
        }
        
        for (Map.Entry<Integer, Boolean> entry : hashMap.entrySet()) {
            if (entry.getValue()) {
                int num = entry.getKey() + 1;
                int count = 1;
                while(hashMap.containsKey(num) && hashMap.get(num)) {
                    hashMap.put(num, false);
                    count++;
                    num++;
                }
                num = entry.getKey() - 1;
                while(hashMap.containsKey(num) && hashMap.get(num)) {
                    hashMap.put(num, false);
                    count++;
                    num--;
                }
                ret = Math.max(ret, count);
            }
        }
        
        return ret;
    }
}