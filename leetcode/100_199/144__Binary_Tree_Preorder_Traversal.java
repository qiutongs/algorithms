class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        List<Integer> ret = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while(stack.isEmpty() == false) {
            TreeNode topNode = stack.pop();
            ret.add(topNode.val);
            
            if (topNode.right != null) {
                stack.push(topNode.right);
            }
            if (topNode.left != null) {
                stack.push(topNode.left);
            }
        }
        
        return ret;
    }
}