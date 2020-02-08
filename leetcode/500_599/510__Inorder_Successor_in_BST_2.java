/*
Given a node in a binary search tree, find the in-order successor of that node in the BST.

If that node has no in-order successor, return null.

The successor of a node is the node with the smallest key greater than node.val.

You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node.

Follow up:

Could you solve it without looking up any of the node's values?
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/
class Solution {
    public Node inorderSuccessor(Node node) {
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            return findLastGreaterAncestor(node);
        }
    }
    
    private Node getLeftMost(Node root) {
        Node cur = root;
        while(cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }
    
    private Node findLastGreaterAncestor(Node node) {
        Node ret = node.parent, cur = node;
        while(ret != null && ret.right == cur) {
            cur = ret;
            ret = ret.parent;
        }
        return ret;
    }
}