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
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        int leftToRight = maxPathFrom(root.left) + maxPathFrom(root.right);
        
        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);
        
        return Math.max(Math.max(leftDiameter, rightDiameter), leftToRight);
    }
    
    private int maxPathFrom(TreeNode node){
        if (node == null) return 0;
        
        int left = maxPathFrom(node.left);
        int right = maxPathFrom(node.right);
        
        return Math.max(left+1, right+1);
    }
}