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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null) {
            return null;
        }
        if (to_delete == null || to_delete.length == 0) {
            return Arrays.asList(root);
        }
        Set<Integer> toDeleteSet = new HashSet<>();
        for (int d : to_delete) {
            toDeleteSet.add(d);
        }
        
        List<TreeNode> ret = new ArrayList<>();
        delete(root, null, toDeleteSet, ret);
        return ret;
    }
    
    private void delete(TreeNode node, TreeNode parent, Set<Integer> toDeleteSet, List<TreeNode> ret) {
        if (node == null) {
            return;
        }
        if (toDeleteSet.contains(node.val)) {
            delete(node, parent);
            delete(node.left, null, toDeleteSet, ret);
            delete(node.right, null, toDeleteSet, ret);
        } else {
            if (parent == null) {
                ret.add(node);
            }
            delete(node.left, node, toDeleteSet, ret);
            delete(node.right, node, toDeleteSet, ret);
        }
    }
    
    private void delete(TreeNode node, TreeNode parent) {
        if (parent != null) {
            if (node == parent.left) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
    }
}