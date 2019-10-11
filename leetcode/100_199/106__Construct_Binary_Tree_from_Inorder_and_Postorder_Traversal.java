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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    
    private TreeNode helper(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR) {
        // postR - postL == inR - inL
        if (postL > postR) {
            return null;
        }
        
        // Left Subtree | Right Subtree | Root
        TreeNode root = new TreeNode(postorder[postR]);
        
        // Left Subtree | Root | Right Subtree
        int rootIndex = indexOf(inorder, postorder[postR], inL, inR);
        int leftLength = rootIndex - inL;
        
        root.left = helper(inorder, inL, rootIndex - 1, postorder, postL, postL + leftLength - 1);
        root.right = helper(inorder, rootIndex + 1, inR, postorder, postL + leftLength, postR - 1);
        
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