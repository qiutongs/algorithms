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
    public int sumNumbers(TreeNode root) {
        int[] result = {0};
        sumNumbers(result, 0, root);
        return result[0];
    }
    
    private void sumNumbers(int[] result, int curSum, TreeNode node){
        if (node == null) return;
        
        if (node.left == null && node.right == null) {
            result[0] += curSum*10 + node.val;
            return;
        }
        
        sumNumbers(result, curSum*10 + node.val, node.left);
        sumNumbers(result, curSum*10 + node.val, node.right);
    }
}