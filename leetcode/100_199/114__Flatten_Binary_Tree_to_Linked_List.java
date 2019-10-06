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
    private TreeNode dummyHead = new TreeNode(0);
    private TreeNode tail = dummyHead;
    
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        TreeNode tmpLeft = root.left;
        TreeNode tmpRight = root.right;
        
        tail.right = root;
        tail = root;
        root.left = null;
        
        flatten(tmpLeft);
        flatten(tmpRight);
    }
}

class Solution2 {
    public void flatten(TreeNode root) {
        flattenHelper(root);
    }
    
    private TreeNode flattenHelper(TreeNode node) {
        if (node == null) {
            return null;
        }
        
        TreeNode ret = node;
        
        TreeNode leftTail = flattenHelper(node.left);
        TreeNode rightTail = flattenHelper(node.right);
        
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
            ret = leftTail;
        }
        
        if (rightTail != null) {
            ret = rightTail;
        }
        
        return ret;
    }
}