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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        
        TreeNode cur = root, parent = null;
        while(cur != null) {
            if (key < cur.val) {
                parent = cur;
                cur = cur.left;
            } else if (key > cur.val) {
                parent = cur;
                cur = cur.right;
            } else {
                break;
            }
        }
        
        if (cur == null) {
            return root;
        } else {
            TreeNode newNode = deleteNode(cur, parent);
            return cur == root ? newNode : root;
        }
    }
    
    private TreeNode deleteNode(TreeNode node, TreeNode parent) {
        if (node.left == null || node.right == null) {
            TreeNode newNode = node.left == null ? node.right : node.left;
            replace(parent, node, newNode);
            return newNode;
        } else {
            TreeNode rightMost = deleteRightMost(node.left, node);
            node.val = rightMost.val;
            return node;
        }
    }
    
    private TreeNode deleteRightMost(TreeNode node, TreeNode parent) {
        while(node.right != null) {
            parent = node;
            node = node.right;
        }
        deleteNode(node, parent);
        return node;
    }
    
    private void replace(TreeNode parent, TreeNode node, TreeNode newNode) {
        if (parent != null) {
            if (node == parent.left) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
    }
}