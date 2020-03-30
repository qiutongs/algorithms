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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return helper(root).node;
    }
    
    private NodeInfo helper(TreeNode node) {
        if (node == null) {
            return new NodeInfo(null, 0);
        }
        NodeInfo leftInfo = helper(node.left);
        NodeInfo rightInfo = helper(node.right);
        
        if (leftInfo.h > rightInfo.h) {
            return new NodeInfo(leftInfo.node, leftInfo.h + 1);
        } else if (leftInfo.h < rightInfo.h) {
            return new NodeInfo(rightInfo.node, rightInfo.h + 1);
        } else {
            return new NodeInfo(node, leftInfo.h + 1);
        }
    }
    
    private class NodeInfo {
        TreeNode node;
        int h;
        NodeInfo(TreeNode node, int h) {
            this.node = node;
            this.h = h;
        }
    }
}