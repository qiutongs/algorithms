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
class BSTIterator1 {

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
// O(1) time(avg): next and hasNext
// O(n) time(worst): next and hasNext
// O(h) space
class BSTIterator2 {

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