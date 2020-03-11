class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        List<Integer> ret = new LinkedList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        HashSet<TreeNode> expanded = new HashSet<>();
        stack.push(root);
        
        while(stack.isEmpty() == false) {
            TreeNode top = stack.peek();
            if (expanded.contains(top) == false) {
                expanded.add(top);
                stack.pop();
                if (top.right != null) {
                    stack.push(top.right);
                }
                stack.push(top);
                if (top.left != null) {
                    stack.push(top.left);
                }
            } else {
                ret.add(stack.pop().val);
            }
        }
        
        return ret;
    }
}

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