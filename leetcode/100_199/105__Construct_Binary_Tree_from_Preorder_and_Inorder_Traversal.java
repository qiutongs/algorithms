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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR) {
        // preR - preL == inR - inL
        if (preL > preR) {
            return null;
        }
        
        // Root | Left Subtree | Right Subtree
        TreeNode root = new TreeNode(preorder[preL]);
        
        // Left Subtree | Root | Right Subtree
        int rootIndex = indexOf(inorder, preorder[preL], inL, inR);
        int leftLength = rootIndex - inL;
        
        root.left = helper(preorder, preL + 1, preL + leftLength, inorder, inL, rootIndex - 1);
        root.right = helper(preorder, preL + leftLength + 1, preR, inorder, rootIndex + 1, inR);
        
        return root;
    }
    
    private int indexOf(int[] arr, int target, int l, int r) {
        for (int i = l; i <= r; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}