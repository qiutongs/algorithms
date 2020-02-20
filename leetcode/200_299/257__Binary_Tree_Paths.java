/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// DFS
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<String> ret = new LinkedList<>();
        dfs(root, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(TreeNode node, List<String> curPath, List<String> ret) {
        curPath.add(String.valueOf(node.val));
        
        if (node.left == null && node.right == null) {
            ret.add(output(curPath));
        }
        if (node.left != null) {
            dfs(node.left, curPath, ret);
        }
        if (node.right != null) {
            dfs(node.right, curPath, ret);
        }
        
        curPath.remove(curPath.size() - 1);
    }
    
    private String output(List<String> path) {
        StringBuilder sb = new StringBuilder();
        sb.append(path.get(0));
        for (int i = 1; i < path.size(); i++) {
            sb.append("->");
            sb.append(path.get(i));
        }
        return sb.toString();
    }
}

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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new LinkedList<>();
        
        if (root == null) {
            return ret;
        }
        
        if (root.left == null && root.right == null) {
            ret.add(String.valueOf(root.val));
            return ret;
        }
        
        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);
        
        for (String path : leftPaths) {
            ret.add(String.valueOf(root.val) + "->" + path);
        }
        
        for (String path : rightPaths) {
            ret.add(String.valueOf(root.val) + "->" + path);
        }
        
        return ret;
    }
}