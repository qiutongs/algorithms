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