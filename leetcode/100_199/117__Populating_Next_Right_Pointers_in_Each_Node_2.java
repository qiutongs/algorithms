/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        helper(root);
        return root;
    }
    
    private void helper(Node node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            return;
        }
        Node next = getFirstChild(node);
        if (node.right != null) {
            node.right.next = next;
            next = node.right;
        }
        if (node.left != null) {
            node.left.next = next;
        }
        helper(node.right);
        helper(node.left);
    }
    
    private Node getFirstChild(Node node) {
        Node cur = node.next;
        while(cur != null && cur.left == null && cur.right == null) {
            cur = cur.next;
        }
        return cur == null ? null : cur.left != null ? cur.left : cur.right;
    }
}