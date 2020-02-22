// Two pointer: find min diff between two sorted array
// Time: O(M + N)
// Space: O(1)
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int ret = Integer.MAX_VALUE;
        int p1 = 0, p2 = 0;
        while(true) {
            p1 = getNext(words, word1, p1);
            p2 = getNext(words, word2, p2);
            if (p1 == words.length || p2 == words.length) {
                break;
            }
            
            ret = Math.min(ret, Math.abs(p1 - p2));
            if (p1 < p2) {
                p1++;
            } else {
                p2++;
            }
        }
        return ret;
    }
    
    private int getNext(String[] words, String word, int index) {
        while(index < words.length && words[index].equals(word) == false) {
            index++;
        }
        return index;
    }
}