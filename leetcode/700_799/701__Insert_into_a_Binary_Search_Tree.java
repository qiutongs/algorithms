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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode curNode = root;
        TreeNode newNode = new TreeNode(val);
        
        while(curNode != null) {
            if (curNode.val < val) {
                if (curNode.right == null) {
                    curNode.right = newNode;
                    break;
                } else {
                    curNode = curNode.right;
                }
            } else if (curNode.val > val) {
                if (curNode.left == null) {
                    curNode.left = newNode;
                    break;
                } else {
                    curNode = curNode.left;
                }
            } else {
                break;
            }
        }
        
        return root == null ? newNode : root;
    }
}