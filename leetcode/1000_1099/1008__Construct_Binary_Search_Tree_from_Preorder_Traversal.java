// Partition the preorder array based on the value of root
// Time: T(N) = 2T(N / 2) + O(N) -> T(NlogN)
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1);
    }
    
    private TreeNode helper(int[] preorder, int l, int r) {
        if (l > r) {
            return null;
        }
        TreeNode ret = new TreeNode(preorder[l]);
        int partitionIdx = firstLargerIndex(preorder, l + 1, r, preorder[l]);
        ret.left = helper(preorder, l + 1, partitionIdx - 1);
        ret.right = helper(preorder, partitionIdx, r);
        return ret;
    }
    
    private int firstLargerIndex(int[] preorder, int l, int r, int val) {
        for (int i = l; i <= r; i++) {
            if (preorder[i] > val) {
                return i;
            }
        }
        return r + 1;
    }
}

// Time: O(N)
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE, new int[]{0});
    }
    
    private TreeNode helper(int[] preorder, int min, int max, int[] index) {
        if (index[0] >= preorder.length || preorder[index[0]] >= max || preorder[index[0]] <= min) {
            return null;
        }
        TreeNode ret = new TreeNode(preorder[index[0]]);
        index[0]++;
        ret.left = helper(preorder, min, ret.val, index);
        ret.right = helper(preorder, ret.val, max, index);
        return ret;
    }
}