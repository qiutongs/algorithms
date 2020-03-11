/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        helper(head);
        return head;
    }
    
    private Node helper(Node head) {
        Node cur = head;
        Node tail = cur;
        
        while(cur != null) {
            tail = cur;
            Node next = cur.next;
            if (cur.child != null) {
                Node childTail = helper(cur.child);
                tail = childTail;
                
                childTail.next = next;
                if (next != null) {
                    next.prev = childTail;
                }

                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            }
            cur = next;
        }
        return tail;
    }
}