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
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        preorder(t, sb);
        return sb.toString();
    }
    
    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        
        sb.append(node.val);
        
        if (node.left != null || node.right != null) {
            sb.append("(");
            preorder(node.left, sb);
            sb.append(")");
        }

        if (node.right != null) {
            sb.append("(");
            preorder(node.right, sb);
            sb.append(")");
        }
    }
}