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
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root).sumOfTilt;
    }
    
    private NodeInfo helper(TreeNode node) {
        if (node == null) {
            return new NodeInfo(0, 0);
        }
        NodeInfo leftInfo = helper(node.left);
        NodeInfo rightInfo = helper(node.right);
        int tilt = Math.abs(leftInfo.sum - rightInfo.sum);
        return new NodeInfo(leftInfo.sum + rightInfo.sum + node.val,
                            leftInfo.sumOfTilt + rightInfo.sumOfTilt + tilt);
    }
    
    private class NodeInfo {
        int sum;
        int sumOfTilt;
        NodeInfo(int sum, int sumOfTilt) {
            this.sum = sum;
            this.sumOfTilt = sumOfTilt;
        }
    }
}