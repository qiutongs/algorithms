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
    public int closestValue(TreeNode root, double target) {
        TreeNode ret = root;
        TreeNode cur = root;
        while(cur != null) {
            if (diff(cur.val, target) < diff(ret.val, target)) {
                ret = cur;
            }
            if (target < cur.val) {
                cur = cur.left;
            } else if (target > cur.val) {
                cur = cur.right;
            } else {
                break;
            }
        }
        return ret.val;
    }
            
    private double diff(int val, double target) {
        return Math.abs((double)val - target);
    }
}