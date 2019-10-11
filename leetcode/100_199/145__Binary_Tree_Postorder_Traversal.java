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
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        List<Integer> ret = new LinkedList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        
        stack.push(root);
        
        while(stack.empty() == false) {
            TreeNode topNode = stack.pop();
            ret.add(topNode.val);
            
            if (topNode.left != null) {
                stack.push(topNode.left);
            }
            
            if (topNode.right != null) {
                stack.push(topNode.right);
            }
        }
        
        Collections.reverse(ret);
        return ret;
    }
}