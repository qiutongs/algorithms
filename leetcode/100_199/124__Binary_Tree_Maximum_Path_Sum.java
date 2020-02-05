/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Recursion + memo 
class Solution {
    private HashMap<TreeNode, Integer> memo = new HashMap<>();
    
    public int maxPathSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        
        int leftMaxFrom = maxPathSumFrom(root.left);
        int rightMaxFrom = maxPathSumFrom(root.right);

        int leftToRightMax = root.val;
        leftToRightMax += leftMaxFrom > 0 ? leftMaxFrom : 0;
        leftToRightMax += rightMaxFrom > 0 ? rightMaxFrom : 0;
        
        int leftMax = maxPathSum(root.left);
        int rightMax = maxPathSum(root.right);
        
        return Math.max(Math.max(leftMax, rightMax), leftToRightMax);
    }
    
    private int maxPathSumFrom(TreeNode node){
        if (node == null) {
            return 0;
        }
        if (memo.containsKey(node)) {
            return memo.get(node);
        }
        
        int leftMax = maxPathSumFrom(node.left);
        int rightMax = maxPathSumFrom(node.right);
        
        int ret = Math.max(Math.max(node.val + leftMax, node.val + rightMax), node.val);
        memo.put(node, ret);
        return ret;
    }
}

// Raw Recursion
class Solution {
    public int maxPathSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        
        int leftMaxFrom = maxPathSumFrom(root.left);
        int rightMaxFrom = maxPathSumFrom(root.right);

        int leftToRightMax = root.val;
        leftToRightMax += leftMaxFrom > 0 ? leftMaxFrom : 0;
        leftToRightMax += rightMaxFrom > 0 ? rightMaxFrom : 0;
        
        int leftMax = maxPathSum(root.left);
        int rightMax = maxPathSum(root.right);
        
        return Math.max(Math.max(leftMax, rightMax), leftToRightMax);
    }
    
    private int maxPathSumFrom(TreeNode node){
        if (node == null) return 0;
        
        int leftMax = maxPathSumFrom(node.left);
        int rightMax = maxPathSumFrom(node.right);
        
        return Math.max(Math.max(node.val + leftMax, node.val + rightMax), node.val);
    }
}