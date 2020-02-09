// Hashtable
// Time O(n*m)
// Space O(m)
// Ref https://leetcode.com/problems/substring-with-concatenation-of-all-words/discuss/13664/Simple-Java-Solution-with-Two-Pointers-and-Map
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s.isEmpty() || words.length == 0) {
            return Collections.emptyList();
        }
        int wLen = words[0].length();
        int size = words.length;
        HashMap<String, Integer> vfMap = new HashMap<>();
        for (String word : words) {
            vfMap.compute(word, (k, v) -> v == null ? 1 : v + 1);
        }
        
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i + wLen * size <= s.length(); i++) {
            if (isConcat(s, i, vfMap, wLen, size)) {
                ret.add(i);
            }
        }
        return ret;
    }
    
    private boolean isConcat(String s, int offset, HashMap<String, Integer> vfMap, int wLen, int size) {
        HashMap<String, Integer> vfMapCopy = new HashMap<>(vfMap);
        while(size > 0) {
            String word = s.substring(offset, offset + wLen);
            Integer freq = vfMapCopy.get(word);
            if (freq != null && freq > 0) {
                vfMapCopy.put(word, freq - 1);
            } else {
                return false;
            }
            offset += wLen;
            size--;
        }
        return true;
    }
}