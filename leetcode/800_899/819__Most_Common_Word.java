// Hashtable: value frequency map
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] words = paragraph.split("[,!?';\\.\\s]");
        HashMap<String, Integer> vfMap = new HashMap<>();
        HashSet<String> banSet = new HashSet<>();

        for (String word : words) {
            if (word.isEmpty() == false) {
                vfMap.compute(word.toLowerCase(), (k, v) -> v == null ? 1 : v + 1);
            }
        }
        for (String word : banned) {
            banSet.add(word);
        }

        String ret = null;
        int max = 0;
        for (String word : vfMap.keySet()) {
            if (banSet.contains(word) == false) {
                if (vfMap.get(word) > max) {
                    ret = word;
                    max = vfMap.get(word);
                }
            }
        }
        return ret;
    }
}