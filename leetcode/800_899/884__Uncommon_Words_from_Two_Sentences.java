// Hashtable:
class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        String[] strsA = A.split("\\s+");
        String[] strsB = B.split("\\s+");
        
        HashMap<String, Integer> vfMap = new HashMap<>();
        for (String str : strsA) {
            vfMap.compute(str, (k, v) -> v == null ? 1 : v + 1);
        }
        for (String str : strsB) {
            vfMap.compute(str, (k, v) -> v == null ? 1 : v + 1);
        }

        List<String> ret = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : vfMap.entrySet()) {
            if (entry.getValue().intValue() == 1) {
                ret.add(entry.getKey());
            }
        }
        return ret.toArray(new String[0]);
    }
}