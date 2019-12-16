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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return helper(nums, 0, nums.length - 1);
    }
    
    private TreeNode helper(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int maxIdx = getMaxIdx(nums, l, r);
        TreeNode ret = new TreeNode(nums[maxIdx]);
        ret.left = helper(nums, l, maxIdx - 1);
        ret.right = helper(nums, maxIdx + 1, r);
        return ret;
    }
    
    private int getMaxIdx(int[] nums, int l, int r) {
        int ret = l;
        for (int i = l + 1; i <= r; i++) {
            ret = nums[i] > nums[ret] ? i : ret;
        }
        return ret;
    }
}