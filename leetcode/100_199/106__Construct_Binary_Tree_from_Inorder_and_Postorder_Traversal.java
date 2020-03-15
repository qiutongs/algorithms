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

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        return helper(postorder, inorder, new int[]{postorder.length - 1}, new int[]{inorder.length - 1}, Integer.MAX_VALUE);
    }
    
    private TreeNode helper(int[] postorder, int[] inorder, int[] postIdx, int[] inIdx, int parentVal) {
        if (postIdx[0] == -1 || inorder[inIdx[0]] == parentVal) {
            return null;
        }
        TreeNode ret = new TreeNode(postorder[postIdx[0]]);
        postIdx[0]--;
        ret.right = helper(postorder, inorder, postIdx, inIdx, ret.val);
        inIdx[0]--;
        ret.left = helper(postorder, inorder, postIdx, inIdx, parentVal);
        return ret;
    }
}