/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// O(1) time(worst): next and hasNext
// O(n) space
class BSTIterator {

    private List<Integer> nodes = new ArrayList<>();
    private int index = 0;
    
    public BSTIterator(TreeNode root) {
        inorder(root);
    }
    
    /** @return the next smallest number */
    public int next() {
        return nodes.get(index++);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return index < nodes.size();
    }
    
    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        
        inorder(node.left);
        
        nodes.add(node.val);
        
        inorder(node.right);
    }
}

// O(h) time(worst): next and hasNext
// O(n) space
class BSTIterator {

    private Stack<TreeNode> stack = new Stack<>();
    private Set<TreeNode> expanded = new HashSet<>();
    
    public BSTIterator(TreeNode root) {
        if (root != null) {
            stack.push(root);
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        while(expanded.contains(stack.peek()) == false) {
            expanded.add(stack.peek());
            TreeNode top = stack.pop();
            if (top.right != null) {
                stack.push(top.right);
            }
            stack.push(top);
            if (top.left != null) {
                stack.push(top.left);
            }
        }
        return stack.pop().val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack.isEmpty() == false;
    }
}

// O(1) time(avg): next and hasNext
// O(h) time(worst): next and hasNext
// O(h) space
class BSTIterator {

    private Stack<TreeNode> stack = new Stack<>();
    private TreeNode curNode = null;
    
    public BSTIterator(TreeNode root) {
        curNode = root;
    }
    
    /** @return the next smallest number */
    public int next() {
        while(curNode != null) {
            stack.push(curNode);
            curNode = curNode.left;
        }

        TreeNode topNode = stack.pop();
        curNode = topNode.right;
        
        return topNode.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return curNode != null || stack.empty() == false;
    }
}