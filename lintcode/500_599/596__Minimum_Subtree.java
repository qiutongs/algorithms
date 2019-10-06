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
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
        TreeNode[] ret = new TreeNode[1];
        int[] minSum = { Integer.MAX_VALUE };
        
        findSubtreeHelper(root, ret, minSum);
        
        return ret[0];
    }
    
    private int findSubtreeHelper(TreeNode node, TreeNode[] ret, int[] minSum) {
        if (node == null) {
            return 0;
        }
        
        int sum = findSubtreeHelper(node.left, ret, minSum) 
                + findSubtreeHelper(node.right, ret, minSum)
                + node.val;
        
        if (sum < minSum[0]) {
            minSum[0] = sum;
            ret[0] = node;
        }
       
        return sum;
    }
}