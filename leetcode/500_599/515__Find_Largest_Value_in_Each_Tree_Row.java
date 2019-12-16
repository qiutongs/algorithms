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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        dfs(root, 0, ret);
        return ret;
    }
    
    private void dfs(TreeNode node, int level, List<Integer> ret){
        if (node == null) {
            return;
        }
        
        if (level >= ret.size()){
            ret.add(node.val);
        } else {
            int val = ret.get(level);
            ret.set(level, val > node.val? val : node.val );
        }
        
        dfs(node.left, level + 1, ret);
        dfs(node.right, level + 1, ret);
    }
}