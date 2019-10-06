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
    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root).isBalanced;
    }
    
    private NodeProperty isBalancedHelper(TreeNode node) {
        if (node == null) {
            return new NodeProperty(true, 0);
        }
        
        NodeProperty leftPro = isBalancedHelper(node.left);
        NodeProperty rightPro = isBalancedHelper(node.right);
        
        boolean isBalanced = leftPro.isBalanced && rightPro.isBalanced && Math.abs(leftPro.height - rightPro.height) <= 1;
        
        return new NodeProperty(isBalanced, Math.max(leftPro.height, rightPro.height) + 1);
    }
    
    private class NodeProperty {
        boolean isBalanced;
        int height;
        
        NodeProperty(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
}