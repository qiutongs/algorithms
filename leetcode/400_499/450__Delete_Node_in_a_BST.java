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
            TreeNode newNode = deleteNode(cur);
            replace(parent, cur, newNode);
            return cur == root ? newNode : root;
        }
    }
    
    private TreeNode deleteNode(TreeNode node) {
        if (node.left == null || node.right == null) {
            return node.left == null ? node.right : node.left;
        } else {
            TreeNode parent = node, cur = node.left;
            while(cur.right != null) {
                parent = cur;
                cur = cur.right;
            }
            replace(parent, cur, cur.left);
            // OR:
            // node.val = cur.val;
            // return node;
            cur.left = node.left;
            cur.right = node.right;
            return cur;
        }
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