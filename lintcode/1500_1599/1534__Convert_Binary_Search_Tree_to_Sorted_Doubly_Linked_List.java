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
    private TreeNode dummyHead = new TreeNode(0);
    private TreeNode tail = dummyHead;
    
    /**
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        inorder(root);
        
        // make circular
        tail.right = dummyHead.right;
        dummyHead.right.left = tail;
        
        return dummyHead.right;
    }
    
    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        
        inorder(node.left);
        
        tail.right = node;
        node.left = tail;
        tail = node;
        
        inorder(node.right);
    }
}