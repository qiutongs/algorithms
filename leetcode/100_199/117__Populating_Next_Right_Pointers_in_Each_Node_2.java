class Solution {
    public Node connect(Node root) {
        helper(root);
        return root;
    }
    
    private void helper(Node node) {
        if (node == null) {
            return;
        } else if (node.left == null && node.right == null) {
            return;
        }
        Node leftmostChild = getLeftmostChild(node);
        if (node.right != null) {
            node.right.next = leftmostChild;
            leftmostChild = node.right;
        }
        if (node.left != null) {
            node.left.next = leftmostChild;
        }
        helper(node.right);
        helper(node.left);
    }
    
    private Node getLeftmostChild(Node node) {
        node = node.next;
        while(node != null) {
            if (node.left != null) {
                return node.left;
            }
            if (node.right != null) {
                return node.right;
            }
            node = node.next;
        }
        return null;
    }
}