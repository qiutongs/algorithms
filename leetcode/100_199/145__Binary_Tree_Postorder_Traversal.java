/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// https://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/
// https://www.jiuzhang.com/solution/binary-tree-postorder-traversal/
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
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
                if (top.right != null) {
                    stack.push(top.right);
                }
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

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        List<Integer> ret = new LinkedList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prev = null;
        
        while(stack.isEmpty() == false) {
            TreeNode cur = stack.peek();
            if (isGoingDown(cur, prev)) {
                if (cur.left != null) {
                    stack.push(cur.left);
                } else if (cur.right != null) {
                    stack.push(cur.right);
                } else {
                    ret.add(stack.pop().val);
                }
            } else if (cur.left == prev) {
                if (cur.right != null) {
                    stack.push(cur.right);
                } else {
                    ret.add(stack.pop().val);
                }
            } else if (cur.right == prev) {
                ret.add(stack.pop().val);
            } else {
                throw new RuntimeException();
            }
            prev = cur;
        }
        
        return ret;
    }
    
    private boolean isGoingDown(TreeNode cur, TreeNode prev) {
        return prev == null || prev.left == cur || prev.right == cur;
    }
}

