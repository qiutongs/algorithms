// Locate coresponding root in post
// Note: favor left. [1,2] [2,1] -> [1,2], not [1,null,2]
// Time: O(N^2) or O(N*H)
// Space: O(H)
class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return helper(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }
    
    private TreeNode helper(int[] pre, int preL, int preR, int[] post, int postL, int postR) {
        if (preL > preR) {
            return null;
        }
        TreeNode ret = new TreeNode(pre[preL]);
        if (preL + 1 <= preR) {
            int postLeftIdx = indexOf(post, postL, postR, pre[preL + 1]);
            int size = postLeftIdx - postL + 1;
            ret.left = helper(pre, preL + 1, preL + size, post, postL, postLeftIdx);
            ret.right = helper(pre, preL + size + 1, preR, post, postLeftIdx + 1, postR);
        }
        return ret;
    }
    
    private int indexOf(int[] arr, int l, int r, int key) {
        for (int i = l; i <= r; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
}

// Smart Index
// Ref: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/discuss/161268/C%2B%2BJavaPython-One-Pass-Real-O(N)
// Time: O(N)
// Space: O(H)
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
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return helper(pre, post, new int[]{0}, new int[]{0}, Integer.MAX_VALUE);
    }
    
    private TreeNode helper(int[] pre, int[] post, int[] preIdx, int[] postIdx, int postEndVal) {
        if (post[postIdx[0]] == postEndVal) {
            return null;
        }
        TreeNode ret = new TreeNode(pre[preIdx[0]]);
        preIdx[0]++;
        ret.left = helper(pre, post, preIdx, postIdx, ret.val);
        ret.right = helper(pre, post, preIdx, postIdx, ret.val);
        postIdx[0]++;
        return ret;
    }
}