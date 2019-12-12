/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // Thelta(n)
    // It has to go over all the nodes
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> ret = new ArrayList<>();
        helper(root, 1, ret);
        return ret;
    }
    
    private void helper(TreeNode node, int level, List<Integer> ret) {
        if (node == null) {
            return;
        }
        if (level > ret.size()) {
            ret.add(node.val);
        }
        helper(node.right, level + 1, ret);
        helper(node.left, level + 1, ret);
    }
}