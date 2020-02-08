/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// O(h) time
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            return getLeftMost(p.right);
        } else {
            return findLastGreaterAncestor(root, p);
        }
    }
    
    private TreeNode getLeftMost(TreeNode root) {
        TreeNode cur = root;
        while(cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }
    
    private TreeNode findLastGreaterAncestor(TreeNode root, TreeNode p) {
        TreeNode ret = null, cur = root;
        while(cur != p) {
            if (cur.val < p.val) {
                cur = cur.right;
            } else {
                ret = cur;
                cur = cur.left;
            }
        }
        return ret;
    }
}