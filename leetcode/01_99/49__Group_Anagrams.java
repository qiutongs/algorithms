// Hashtable + counting sort
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return Collections.emptyList();
        }
        
        List<List<String>> ret = new LinkedList<>();
        
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String k = sort(str);
            if (map.containsKey(k)) {
                map.get(k).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(k, list);
            }
        }
        
        ret.addAll(map.values());
        return ret;
    }
    
    private String sort(String s) {
        char[] arr = s.toCharArray();
        int[] count = new int[26];
        Arrays.fill(count, 0);
        
        for (char c : arr) {
            count[c - 'a']++;
        }

        int j = 0;
        for (int i = 0; i < 26; i++) {
            while(count[i] > 0) {
                arr[j++] = (char)('a' + i);
                count[i]--;
            }
        }
        return new String(arr);
    }
}