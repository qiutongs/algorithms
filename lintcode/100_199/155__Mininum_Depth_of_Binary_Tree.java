/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: The root of binary tree
     * @return: An integer
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        if (root.left == null && root.right == null) {
            return 1;
        }
        
        int leftDepth =  root.left == null ? Integer.MAX_VALUE : minDepth(root.left);
        int rightDepth =  root.right == null ? Integer.MAX_VALUE : minDepth(root.right);
        
        return Math.min(leftDepth, rightDepth) + 1;
    }
}