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

class Solution2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        List<Integer> ret = new LinkedList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        HashSet<TreeNode> visited = new HashSet<>();
        stack.push(root);
        visited.add(root);
        
        while(stack.isEmpty() == false) {
            while(true) {
                TreeNode top = stack.peek();
                boolean hasAdjacent = false;
                
                if (top.right != null && visited.contains(top.right) == false) {
                    visited.add(top.right);
                    stack.push(top.right);
                    hasAdjacent = true;
                }
            
                if (top.left != null && visited.contains(top.left) == false) {
                    visited.add(top.left);
                    stack.push(top.left);
                    hasAdjacent = true;
                }
                
                if (hasAdjacent == false) {
                    break;
                }
            }
            
            ret.add(stack.pop().val);
        }
        
        return ret;
    }
}

class Solution3 {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        List<Integer> ret = new LinkedList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        HashSet<TreeNode> visited = new HashSet<>();
        stack.push(root);
        
        while(stack.isEmpty() == false) {
            TreeNode top = stack.peek();
            
            if (visited.contains(top)) {
                ret.add(stack.pop().val);
            } else {
                visited.add(top);
                
                if (top.right != null) {
                    stack.push(top.right);
                }
                
                if (top.left != null) {
                    stack.push(top.left);
                }
            }
        }
        
        return ret;
    }
}