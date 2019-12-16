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
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        long ret = helper(root, root.val);
        return ret == Long.MAX_VALUE ? -1 : (int)ret;
    }
    
    private long helper(TreeNode node, int min) {
        if (node == null) {
            return Long.MAX_VALUE;
        }
        if (node.val > min) {
            return node.val;
        }
        return Math.min(helper(node.left, min), helper(node.right, min));
    }
}