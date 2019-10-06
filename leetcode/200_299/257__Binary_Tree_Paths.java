/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution1 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new LinkedList<>();
        binaryTreePaths(result, "", root);
        return result;
    }
    
    private void binaryTreePaths(List<String> result, String cur, TreeNode node){
        if (node == null) return;
        
        if (node.left == null && node.right == null){
            result.add(cur+String.valueOf(node.val));
            return;
        }
        
        binaryTreePaths(result, cur+String.valueOf(node.val)+"->", node.left);
        binaryTreePaths(result, cur+String.valueOf(node.val)+"->", node.right);
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
class Solution2 {
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