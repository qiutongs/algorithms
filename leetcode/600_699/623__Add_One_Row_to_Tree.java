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
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode ret = new TreeNode(v);
            ret.left = root;
            return ret;
        }
        helper(root, 1, v, d);
        return root;
    }
    
    private void helper(TreeNode node, int level, int v, int d) {
        if (node == null) {
            return;
        }
        if (level == d - 1) {
            TreeNode left = new TreeNode(v);
            TreeNode right = new TreeNode(v);
            left.left = node.left;
            right.right = node.right;
            node.left = left;
            node.right = right;
            return;
        }
        helper(node.left, level + 1, v, d);
        helper(node.right, level + 1, v, d);
    }
}