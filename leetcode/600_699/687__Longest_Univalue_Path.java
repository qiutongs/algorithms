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
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        
        int leftToRight = longestPathFrom(root.left, root.val) + longestPathFrom(root.right, root.val);
        int left = longestUnivaluePath(root.left);
        int right = longestUnivaluePath(root.right);
        return Math.max(Math.max(left, right), leftToRight);
    }
    
    private int longestPathFrom(TreeNode node, int val) {
        if (node == null) return 0;
        
        if (node.val != val) return 0;
        
        int left = 0, right = 0;
        left = longestPathFrom(node.left, val);
        right = longestPathFrom(node.right, val);
        
        return Math.max(left+1, right+1);
    }
}