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
        if (root == null) {
            return null;
        }
        
        TreeNode curNode = root;
        
        while(curNode != null) {
            if (p.val < curNode.val && q.val < curNode.val) {
                curNode = curNode.left;
            } else if (p.val > curNode.val && q.val > curNode.val) {
                curNode = curNode.right;
            } else {
                return curNode;
            }
        }
        
        return null;
    }
}