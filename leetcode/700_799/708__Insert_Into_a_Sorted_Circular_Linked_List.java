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
        Node prev = head, cur = head.next;
        do {
            // Note: cur == head is the edge case that no proper insertion point has been found.
            // It implies this all nodes have same value  
            if (inbetween(prev, cur, insertVal) || inboundary(prev, cur, insertVal) || cur == head) {
                Node newNode = new Node(insertVal);
                prev.next = newNode;
                newNode.next = cur;
                break;
            }
            prev = cur;
            cur = cur.next;
        } while(prev != head);
        return head;
    }
                
    private boolean inbetween(Node prev, Node cur, int insertVal) {
        return prev.val <= insertVal && cur.val >= insertVal;
    }
                
    private boolean inboundary(Node prev, Node cur, int insertVal) {
        return prev.val > cur.val && (insertVal >= prev.val || insertVal <= cur.val);
    }
}