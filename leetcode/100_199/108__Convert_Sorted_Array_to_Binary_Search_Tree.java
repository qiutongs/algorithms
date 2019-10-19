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
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    
    private TreeNode helper(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        
        if (l == r) {
            return new TreeNode(nums[l]);
        }
        
        int mid = (l + r) / 2;
        
        TreeNode ret = new TreeNode(nums[mid]);
        
        ret.left = helper(nums, l, mid - 1);
        ret.right = helper(nums, mid + 1, r);
        
        return ret;
    }
}