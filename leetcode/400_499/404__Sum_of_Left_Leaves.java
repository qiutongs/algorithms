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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root.left, true) + helper(root.right, false);
    }
    
    private int helper(TreeNode node, boolean leftChild) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return leftChild ? node.val : 0;
        }
        return helper(node.left, true) + helper(node.right, false);
    }
}