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
    public double maximumAverageSubtree(TreeNode root) {
        return helper(root).max;
    }
    
    private NodeInfo helper(TreeNode node) {
        if (node == null) {
            return new NodeInfo(0, 0, 0.0);
        }
        NodeInfo leftInfo = helper(node.left);
        NodeInfo rightInfo = helper(node.right);
        int sum = node.val + leftInfo.sum + rightInfo.sum;
        int size = 1 + leftInfo.size + rightInfo.size;
        double max = Math.max(Math.max(leftInfo.max, rightInfo.max), (double)sum / (double)size);
        return new NodeInfo(sum, size, max);
    }
    
    private class NodeInfo {
        double max;
        int sum;
        int size;
        NodeInfo(int sum, int size, double max) {
            this.sum = sum;
            this.size = size;
            this.max = max;
        }
    }
}