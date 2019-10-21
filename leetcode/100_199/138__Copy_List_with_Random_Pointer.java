/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        copy(head);
        copyRandom(head);
        return split(head);
    }
    
    private void copy(Node head) {
        Node cur = head;
        
        while(cur != null) {
            Node next = cur.next;
            cur.next = new Node(cur.val, next, null);
            cur = next;
        }
    }
    
    private void copyRandom(Node head) {
        Node cur = head;
        
        while(cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
    }
    
    private Node split(Node head) {
        Node dummy = new Node();
        Node tail = dummy;
        
        Node cur = head;
        
        while(cur != null) {
            tail.next = cur.next;
            tail = tail.next;
            
            cur.next = cur.next.next;
            cur = cur.next;
        }
        
        return dummy.next;
    }
}