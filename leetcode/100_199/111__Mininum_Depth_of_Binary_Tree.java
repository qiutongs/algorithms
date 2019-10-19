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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        
        if (root.left == null) return minDepth(root.right)+1;
        if (root.right == null) return minDepth(root.left)+1;
        
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return Math.min(left+1, right+1);
    }
}

class Solution2 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        int depth = 1;
        
        while(queue.isEmpty() == false) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                
                if (node.left == null && node.right == null) {
                    return depth;
                }
                
                if (node.left != null) {
                    queue.add(node.left);
                }
                
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            
            depth++;
        }
        
        return 0;
    }
}