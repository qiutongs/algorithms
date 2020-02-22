// Hashtable: value-index map
// Time: O(M + N)
// Space: O(M + N)
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> viMap1 = new HashMap<>();
        HashMap<String, Integer> viMap2 = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            viMap1.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            viMap2.put(list2[i], i);
        }
        
        List<String> ret = new ArrayList<>();
        int minSum = Integer.MAX_VALUE;
        for (String str : list1) {
            if (viMap2.containsKey(str)) {
                int sum = viMap1.get(str) + viMap2.get(str);
                if (sum < minSum) {
                    minSum = sum;
                    ret.clear();
                    ret.add(str);
                } else if (sum == minSum) {
                    ret.add(str);
                }
            }
        }
        return ret.toArray(new String[0]);
    }
}