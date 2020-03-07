public class Solution {
    /**
     * @param A: An Integer array
     * @return: returns the maximum sum of two numbers
     */
    public int MaximumSum(int[] A) {
        HashMap<Integer, List<Integer>> sdListMap = new HashMap<>();
        for (int num : A) {
            int sd = sumOfDigits(num);
            sdListMap.putIfAbsent(sd, new ArrayList<>(3));
            List<Integer> list = sdListMap.get(sd);
            list.add(num);
            if (list.size() == 3) {
                removeMin(list);
            }
        }
        
        int ret = -1;
        for (Map.Entry<Integer, List<Integer>> entry : sdListMap.entrySet()) {
            if (entry.getValue().size() == 2) {
                List<Integer> list = entry.getValue();
                ret = Math.max(ret, list.get(0) + list.get(1));
            } 
        }
        return ret;
    }
    
    private void removeMin(List<Integer> list) {
        int minIdx = 0;
        for (int i = 1; i < list.size(); i++) {
            minIdx = list.get(i) < list.get(minIdx) ? i : minIdx;
        }
        list.remove(minIdx);
    }
    
    private int sumOfDigits(int x) {
        int ret = 0;
        while(x > 0) {
            ret += x % 10;
            x /= 10;
        }
        return ret;
    }
}