// Compare to storing path for p and q, recursive traverses only once.
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lcaHelper(root, p, q).lca;
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findLCAorPorQ(root, p, q);
    }
    
    private TreeNode findLCAorPorQ(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return null;

        TreeNode leftLCAorPorQ = findLCAorPorQ(node.left, p, q);
        TreeNode rightLCAorPorQ = findLCAorPorQ(node.right, p, q);
        
        if (leftLCAorPorQ != null && rightLCAorPorQ != null){
            return node;
        } else if (leftLCAorPorQ == null && rightLCAorPorQ == null){
            return node == p || node == q ? node : null;
        } else {
            TreeNode foundLCA = leftLCAorPorQ == null ? rightLCAorPorQ : leftLCAorPorQ;
            return node == p || node == q ? node : foundLCA;
        }
    }
}