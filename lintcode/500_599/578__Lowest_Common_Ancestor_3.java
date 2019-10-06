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
    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        return lcaHelper(root, A, B).lca;
    }
    
    TreeProperty lcaHelper(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return new TreeProperty(false, false, null);
        }
        
        TreeProperty leftProp = lcaHelper(node.left, p, q);
        TreeProperty rightProp = lcaHelper(node.right, p, q);
        
        if (leftProp.lca == null && rightProp.lca == null) {
            boolean hasP = leftProp.hasP || rightProp.hasP || node == p;
            boolean hasQ = leftProp.hasQ || rightProp.hasQ || node == q;
            
            if (hasP && hasQ) {
                return new TreeProperty(hasP, hasQ, node);
            } else {
                return new TreeProperty(hasP, hasQ, null);
            }            
        } else {
            return leftProp.lca != null ? leftProp : rightProp;
        }
    }
    
    private class TreeProperty {
        boolean hasP;
        boolean hasQ;
        TreeNode lca;

        TreeProperty(boolean hasP, boolean hasQ, TreeNode lca) {
            this.hasP = hasP;
            this.hasQ = hasQ;
            this.lca = lca;
        }
    }
}