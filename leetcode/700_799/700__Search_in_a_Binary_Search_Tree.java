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
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode curNode = root;
        
        while(curNode != null) {
            if (curNode.val < val) {
                curNode = curNode.right;
            } else if (curNode.val > val) {
                curNode = curNode.left;
            } else {
                return curNode;
            }
        }
        
        return null;
    }
}