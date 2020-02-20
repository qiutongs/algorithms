/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution1 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        return isBSTHelper(root).isBST;
    }
    
    private TreeProperty isBSTHelper(TreeNode node) {
        TreeProperty ret = new TreeProperty(node.val, node.val, true);
        
        if (node.left == null && node.right == null) {
            return ret;
        }
        
        if (node.right != null) {
            TreeProperty rightProp = isBSTHelper(node.right);
            ret.isBST = ret.isBST && rightProp.isBST && node.val < rightProp.min;
            ret.max = rightProp.max;
        } 

        if (node.left != null) {
            TreeProperty leftProp = isBSTHelper(node.left);
            ret.isBST = ret.isBST && leftProp.isBST && leftProp.max < node.val;
            ret.min = leftProp.min;
        }
        
        return ret;
    }
        
    private class TreeProperty {
        int max;
        int min;
        boolean isBST;
        TreeProperty(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }
}

class Solution2 {
    public boolean isValidBST(TreeNode root) {
        return isBSTHelper(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
    
    private boolean isBSTHelper(TreeNode node, int max, int min) {
        if (node == null) {
            return true;
        }
        if (node.val >= max || node.val <= min) {
            return false;
        }
        return isBSTHelper(node.left, node.val, min) && isBSTHelper(node.right, max, node.val);
    }
}