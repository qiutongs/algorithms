/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/
class Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newHead = new Node(insertVal);
            newHead.next = newHead;
            return newHead;
        }
        Node cur = head, next = head.next;
        do {
            if (inbetween(cur, next, insertVal) || inboundary(cur, next, insertVal) || next == head) {
                Node newNode = new Node(insertVal);
                cur.next = newNode;
                newNode.next = next;
                break;
            }
            cur = next;
            next = next.next;
        } while(cur != head);
        return head;
    }
                
    private boolean inbetween(Node cur, Node next, int insertVal) {
        return cur.val <= insertVal && next.val >= insertVal;
    }
                
    private boolean inboundary(Node cur, Node next, int insertVal) {
        return cur.val > next.val && (insertVal >= cur.val || insertVal <= next.val);
    }
}