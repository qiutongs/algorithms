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
     * @return: the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode[] ret = new TreeNode[1];
        double[] maxAvg = { -Double.MAX_VALUE };
        
        findMaxAveHelper(root, ret, maxAvg);
        
        return ret[0];
    }
    
    private TreeProperty findMaxAveHelper(TreeNode node, TreeNode[] maxNode, double[] maxAvg) {
        if (node == null) {
            return new TreeProperty(0, 0);
        }
        
        TreeProperty leftProp = findMaxAveHelper(node.left, maxNode, maxAvg);
        TreeProperty rightProp = findMaxAveHelper(node.right, maxNode, maxAvg);
        
        TreeProperty ret = new TreeProperty(leftProp.sum + rightProp.sum + node.val, leftProp.size + rightProp.size + 1);
        
        if (ret.toAvg() > maxAvg[0]) {
            maxAvg[0] = ret.toAvg();
            maxNode[0] = node;
        }
        
        return ret;
    }
    
    private class TreeProperty {
        int sum;
        int size;

        TreeProperty(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
        
        double toAvg() {
            return (double)sum / (double)size;
        }
    }
}