/*
Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

1. Begin with the first character and then the number of characters abbreviated, which followed by the last character.
2. If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
3. If the abbreviation doesn't make the word shorter, then keep it as original.

Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]

Note:
1. Both n and the length of each word will not exceed 400.
2. The length of each word is greater than 1.
3. The words consist of lowercase English letters only.
4. The return answers should be in the same order as the original array.
*/
// Hashtable + Trie
// Time: O(nl)
// Space: O(nl)
class Solution {
    public List<String> wordsAbbreviation(List<String> dict) {
        HashMap<String, String> wordToAbbMap = new HashMap<>();
        HashMap<String, List<String>> abbToWordsMap = new HashMap<>();
        for (String word : dict) {
            String abb = abbreviate(word);
            wordToAbbMap.put(word, abb);
            abbToWordsMap.putIfAbsent(abb, new ArrayList<>());
            abbToWordsMap.get(abb).add(word);
        }

        for (List<String> wordsList : abbToWordsMap.values()) {
            if (wordsList.size() > 1) {
                TrieNode root = new TrieNode();
                for (String similarWord : wordsList) {
                    insert(root, similarWord);
                }
                for (String similarWord : wordsList) {
                    String newAbb = abbreviate(root, similarWord);
                    wordToAbbMap.put(similarWord, newAbb);
                }
            }
        }
        
        List<String> ret = new ArrayList<>(dict.size());
        for (String word : dict) {
            ret.add(wordToAbbMap.get(word));
        }
        return ret;
    }
    
    private String abbreviate(String word) {
        if (word.length() > 3) {
            return word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() - 1);
        } else {
            return word;
        }
    }
    
    private String abbreviate(TrieNode root, String word) {
        TrieNode cur = root;
        int i = 0;
        for (; i < word.length() - 1; i++) {
            char c = word.charAt(i);
            if (cur.getCount(c) == 1) {
                break;
            }
            cur = cur.get(c);
        }
        if (i <= word.length() - 4) {
            return word.substring(0, i + 1) + (word.length() - i - 2) + word.charAt(word.length() - 1);
        } else {
            return word;
        }
    }
    
    private void insert(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.addIfAbsent(c);
            cur = cur.get(c);
        }
        cur.isKey = true;
    }
    
    private class TrieNode {
        TrieNode[] childs = new TrieNode[26];
        int[] counts = new int[26];
        boolean isKey = false;
        void addIfAbsent(char c) {
            if (childs[c - 'a'] == null) {
                childs[c - 'a'] = new TrieNode();
            }
            counts[c - 'a']++;
        }
        TrieNode get(char c) {
            return childs[c - 'a'];
        }
        int getCount(char c) {
            return counts[c - 'a'];
        }
    }
}