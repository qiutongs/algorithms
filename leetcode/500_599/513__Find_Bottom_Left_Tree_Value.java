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
    private final NodeInfo NULL_NODE = new NodeInfo(0, -1);
    
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root, 0).val;
    }
    
    private NodeInfo helper(TreeNode node, int level){
        if (node == null) {
            return NULL_NODE;
        }
        if (node.left == null && node.right == null) {
            return new NodeInfo(node.val, level);
        }
        NodeInfo leftInfo = helper(node.left, level + 1);
        NodeInfo rightInfo = helper(node.right, level + 1);
        return leftInfo.level >= rightInfo.level ? leftInfo : rightInfo;
    }
    
    private class NodeInfo {
        int val;
        int level;
        NodeInfo(int v, int l){
            val = v; 
            level = l;
        }
    }
}

class Solution {
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] maxDepth = { -1 };
        int[] ret = { 0 };
        dfs(root, 0, maxDepth, ret);
        return ret[0];
    }
    
    private void dfs(TreeNode node, int level, int[] maxDepth, int[] ret) {
        if (node == null) {
            return;
        }
        if (level > maxDepth[0]) {
            maxDepth[0] = level;
            ret[0] = node.val;
        }
        dfs(node.left, level + 1, maxDepth, ret);
        dfs(node.right, level + 1, maxDepth, ret);
    }
}