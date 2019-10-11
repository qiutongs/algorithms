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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<>();
        pathSum(result, new LinkedList<>(), root, sum);
        return result;
    }
    
    private void pathSum(List<List<Integer>> result, LinkedList<Integer> curPath, TreeNode node, int sum){
        if (node == null) return;
        
        if (node.left == null && node.right == null && node.val == sum){
            curPath.add(sum);
            result.add(new LinkedList<>(curPath));
            curPath.removeLast();
            return;
        }
        
        curPath.add(node.val);
        
        pathSum(result, curPath, node.left, sum-node.val);
        pathSum(result, curPath, node.right, sum-node.val);
        
        curPath.removeLast();
    }
}