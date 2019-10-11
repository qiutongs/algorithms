public class Solution {
    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> ret = new LinkedList<>();
        searchHelper(root, k1, k2, ret);
        return ret;
    }
    
    private void searchHelper(TreeNode node, int k1, int k2, List<Integer> ret) {
        if (node == null) {
            return;
        }
        
        searchHelper(node.left, k1, k2, ret);
        
        if (node.val <= k2 && node.val >= k1) {
            ret.add(node.val);
        }
        
        searchHelper(node.right, k1, k2, ret);
    }
}