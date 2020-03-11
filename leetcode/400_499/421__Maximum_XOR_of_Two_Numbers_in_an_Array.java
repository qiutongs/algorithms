// Bit Trie
// Time: O(N)
// Space: O(N)
class Solution {
    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        for (int num : nums) {
            insert(root, num);
        }
        
        int ret = 0;
        for (int num : nums) {
            ret = Math.max(ret, num ^ getMaxMatch(root, num));
        }
        return ret;
    }
    
    private int getMaxMatch(TrieNode root, int val) {
        TrieNode cur = root;
        for (int i = 0; i < 32; i++) {
            int mask = 0x80000000 >>> i;
            int bit = (val & mask) == 0 ? 0 : 1;
            int flip = bit == 1 ? 0 : 1;
            cur = cur.get(flip) != null ? cur.get(flip) : cur.get(bit);
        }
        return cur.val;
    }
    
    private void insert(TrieNode root, int val) {
        TrieNode cur = root;
        for (int i = 0; i < 32; i++) {
            int mask = 0x80000000 >>> i;
            int bit = (val & mask) == 0 ? 0 : 1;
            cur.addIfAbsent(bit);
            cur = cur.get(bit);
        }
        cur.val = val;
    }
    
    private class TrieNode {
        TrieNode[] childs = new TrieNode[2];
        int val = 0;
        void addIfAbsent(int bit) {
            if (childs[bit] == null) {
                childs[bit] = new TrieNode();
            }
        }
        TrieNode get(int bit) {
            return childs[bit];
        }
    }
}