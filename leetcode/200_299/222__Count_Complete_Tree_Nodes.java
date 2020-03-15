/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Time: O(logN * logN)
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int rootH = getH(root);
        if (getH(root.right) == rootH - 1) {
            return (1 << (rootH - 1)) - 1 + countNodes(root.right) + 1;
        } else {
            return (1 << (rootH - 2)) - 1 + countNodes(root.left) + 1;
        }
    }
    
    private int getH(TreeNode node) {
        int h = 0;
        while(node != null) {
            h++;
            node = node.left;
        }
        return h;
    }
}