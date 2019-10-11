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
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        
        int ret = 0;
        
        ret += pathSum(root.left, sum) + pathSum(root.right, sum);
        
        ret += pathSumStartHere(root, sum);
        
        return ret;
    }
    
    private int pathSumStartHere(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        
        int ret = 0;
        
        if (node.val == sum) {
            ret++;
        }

        ret += pathSumStartHere(node.left, sum - node.val)
            + pathSumStartHere(node.right, sum - node.val);
        
        return ret;
    }
}