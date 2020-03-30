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
    private NodeInfo NULL_NODE = new NodeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
    
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root).maxDiff;
    }
    
    private NodeInfo helper(TreeNode node) {
        if (node.left == null && node.right == null) {
            return new NodeInfo(node.val, node.val, 0);
        }
        NodeInfo leftInfo = node.left != null ? helper(node.left) : NULL_NODE;
        NodeInfo rightInfo = node.right != null ? helper(node.right) : NULL_NODE;
        
        int min = Math.min(leftInfo.min, rightInfo.min);
        int max = Math.max(leftInfo.max, rightInfo.max);
        int maxDiff = Math.max(leftInfo.maxDiff, rightInfo.maxDiff);

        maxDiff = Math.max(maxDiff, Math.max(Math.abs(node.val - min), Math.abs(node.val - max)));
        min = Math.min(min, node.val);
        max = Math.max(max, node.val);
        
        return new NodeInfo(min, max, maxDiff);
    }
    
    private class NodeInfo {
        int min;
        int max;
        int maxDiff;
        NodeInfo(int min, int max, int maxDiff) {
            this.min = min;
            this.max = max;
            this.maxDiff = maxDiff;
        }
    }
}