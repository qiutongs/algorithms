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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null || target == null) {
            return Collections.emptyList();
        }
        List<TreeNode> path = new ArrayList<>();
        dfs(root, target, new ArrayList<>(), path);

        List<Integer> ret = new ArrayList<>();
        dfs(root, path.size() - 1, new HashSet<>(path), K, ret);
        return ret;
    }
    
    private void dfs(TreeNode node, TreeNode target, List<TreeNode> curPath, List<TreeNode> ret) {
        if (node == null) {
            return;
        }
        curPath.add(node);
        if (node == target) {
            ret.addAll(curPath);
            return;
        }
        dfs(node.left, target, curPath, ret);
        dfs(node.right, target, curPath, ret);
        curPath.remove(curPath.size() - 1);
    }
    
    private void dfs(TreeNode node, int dis, Set<TreeNode> path, int K, List<Integer> ret) {
        if (node == null) {
            return;
        }
        if (dis == K) {
            ret.add(node.val);
        }
        dfs(node.left, path.contains(node.left) ? dis - 1 : dis + 1, path, K, ret);
        dfs(node.right, path.contains(node.right) ? dis - 1 : dis + 1, path, K, ret);
    }
}