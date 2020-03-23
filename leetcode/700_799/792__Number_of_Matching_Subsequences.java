class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int n = words.length;
        int[] indexes = new int[n];
        Queue<Integer>[] waitQ = (Queue<Integer>[])new Queue[26];
        for (int i = 0; i < 26; i++) {
            waitQ[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            waitQ[words[i].charAt(0) - 'a'].offer(i);
        }
        
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            Queue<Integer> wakenQ = waitQ[c - 'a'];
            int size = wakenQ.size();
            for (int j = 0; j < size; j++) {
                int idx = wakenQ.poll();
                indexes[idx]++;
                if (indexes[idx] < words[idx].length()) {
                    waitQ[words[idx].charAt(indexes[idx]) - 'a'].offer(idx);
                }
            }
        }
        
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (indexes[i] == words[i].length()) {
                ret++;
            }
        }
        return ret;
    }
}

/*
 * Naive soltion has time complexity: n(|S| + |word|)
 * 
 * This solution: |S| + n|word|
 */
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        if (S == null || S.length() == 0 || words == null || words.length == 0) {
            return 0;
        }
        
        // For each letter, there is a wait list of strings.
        ArrayList<LinkedList<String>> waitLists = new ArrayList<>(26);
        // Store the current waiting index in string.
        HashMap<String, Integer> wordIndexMap = new HashMap<>();
        // How many times the same string apprears.
        HashMap<String, Integer> wordCounter = new HashMap<>();
        
        for (int i = 0; i < 26; i++) {
            waitLists.add(new LinkedList<>());
        }
        
        for (String word : words) {
            if (wordCounter.containsKey(word) == false) {
                wordCounter.put(word, 1);
                wordIndexMap.put(word, 0);
                waitLists.get(word.charAt(0) - 'a').add(word);
            } else {
                wordCounter.put(word, wordCounter.get(word) + 1);
            }
        }
        
        int ret = 0;
        
        // Interate each character in 'S'.
        for (int i = 0; i < S.length(); i++) {
            // Find the waiting list for current character.
            LinkedList<String> waitList = waitLists.get(S.charAt(i) - 'a');
            // This is the size of strings to be waken up.
            int curSize = waitList.size();
            
            for (int j = 0; j < curSize; j++) {
                // Wake up.
                String str = waitList.removeFirst();
                
                Integer index = wordIndexMap.get(str);
                index++;
                
                // The end of string means it is a valid subsequence.
                if (index == str.length()) {
                    ret += wordCounter.get(str);
                    wordIndexMap.remove(str);
                    wordCounter.remove(str);
                } else {
                    wordIndexMap.put(str, index);
                    // Add current string to a new waiting list.
                    waitLists.get(str.charAt(index) - 'a').add(str);
                }
            }
        }
        
        return ret;
    }
}