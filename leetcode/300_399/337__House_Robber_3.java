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
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return Math.max(robInclude(root), robExclude(root));
    }
    
    private int robInclude(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        return node.val + robExclude(node.left) + robExclude(node.right);
    }
    
    private int robExclude(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        return rob(node.left) + rob(node.right);
    }
}

// memo
class Solution {
    private HashMap<TreeNode, Integer> inMemo = new HashMap<>();
    private HashMap<TreeNode, Integer> exMemo = new HashMap<>();
    
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return Math.max(robInclude(root), robExclude(root));
    }
    
    private int robInclude(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (inMemo.containsKey(node)) {
            return inMemo.get(node);
        }
        int ret = node.val + robExclude(node.left) + robExclude(node.right);
        inMemo.put(node, ret);
        return ret;
    }
    
    private int robExclude(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (exMemo.containsKey(node)) {
            return exMemo.get(node);
        }
        int ret = rob(node.left) + rob(node.right);
        exMemo.put(node, ret);
        return ret;
    }
}