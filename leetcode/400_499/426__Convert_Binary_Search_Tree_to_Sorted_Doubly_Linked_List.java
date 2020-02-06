/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        NodeInfo rootInfo = helper(root);
        rootInfo.head.left = rootInfo.tail;
        rootInfo.tail.right = rootInfo.head;
        return rootInfo.head;
    }
    
    private NodeInfo helper(Node node) {
        if (node == null) {
            return new NodeInfo(null, null);
        }
        NodeInfo leftInfo = helper(node.left);
        NodeInfo rightInfo = helper(node.right);
        NodeInfo ret = new NodeInfo(node, node);
        if (leftInfo.tail != null) {
            leftInfo.tail.right = node;
            node.left = leftInfo.tail;
            ret.head = leftInfo.head;
        }
        if (rightInfo.head != null) {
            rightInfo.head.left = node;
            node.right = rightInfo.head;
            ret.tail = rightInfo.tail;
        }
        return ret;
    }
    
    class NodeInfo {
        Node head;
        Node tail;
        NodeInfo(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }
}