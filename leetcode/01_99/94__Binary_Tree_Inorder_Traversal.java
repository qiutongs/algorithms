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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new LinkedList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode curNode = root;
        
        while(curNode != null || stack.empty() == false) {
            // push current node and all its left descendants(including itself)
            while(curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            
            // all the left descendants of the top node have been processed
            TreeNode topNode = stack.pop();
            ret.add(topNode.val);

            // now move to its right subtree
            curNode = topNode.right;
        }
        
        return ret;
    }
}