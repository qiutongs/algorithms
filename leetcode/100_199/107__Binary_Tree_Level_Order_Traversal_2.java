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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> ret = new ArrayList<>();
        
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.add(root);
        
        while(queue.isEmpty() == false) {
            List<Integer> level = new ArrayList<>();
            
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                level.add(node.val);
                
                if (node.left != null) {
                    queue.add(node.left);
                }
                
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            
            ret.add(level);
        }
        
        Collections.reverse(ret);
        
        return ret;
    }
}