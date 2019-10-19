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
    TreeNode pre = null;
    TreeNode first = null;
    TreeNode second = null;
    
    public void recoverTree(TreeNode root) {
        inorder(root);
        
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
    
    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        
        inorder(node.left);
        
        if (pre != null) {
            if (node.val < pre.val) {
                if (first == null) {
                    first = pre;
                }
                second = node;
            }
        }
        
        pre = node;
        
        inorder(node.right);
    }
}