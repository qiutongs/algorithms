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
    public int maxPathSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        
        int fromLeftMax = maxPathSumFrom(root.left);
        int fromRightMax = maxPathSumFrom(root.right);

        int fromLeftToRightMax;
        
        if (fromLeftMax < 0 && fromRightMax < 0){
            fromLeftToRightMax = root.val;
        } else if (fromLeftMax < 0 || fromRightMax < 0){
            fromLeftToRightMax = Math.max(fromLeftMax, fromRightMax) + root.val;
        } else {
            fromLeftToRightMax = fromLeftMax + fromRightMax + root.val;
        }
        
        int leftMax = maxPathSum(root.left);
        int rightMax = maxPathSum(root.right);
        
        return Math.max(Math.max(leftMax, rightMax), fromLeftToRightMax);
    }
    
    private int maxPathSumFrom(TreeNode node){
        if (node == null) return 0;
        
        int leftMax = maxPathSumFrom(node.left);
        int rightMax = maxPathSumFrom(node.right);
        
        return Math.max(Math.max(node.val + leftMax, node.val + rightMax), node.val);
    }
}