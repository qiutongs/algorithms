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
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return Math.max(robInclude(root), robExclude(root));
    }
    
    private int robInclude(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        return node.val + robExclude(node.left) + robExclude(node.right);
    }
    
    private int robExclude(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        return rob(node.left) + rob(node.right);
    }
}