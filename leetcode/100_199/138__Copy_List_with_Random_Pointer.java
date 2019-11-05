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

class Solution2 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        Node ret = new Node(head.val, null, null);
        Node tail = ret;
        HashMap<Node, Node> hashMap = new HashMap<>();
        hashMap.put(head, ret);
        
        Node cur = head.next;
        while(cur != null) {
            tail.next = new Node(cur.val, null, null);
            tail = tail.next;
            hashMap.put(cur, tail);
            cur = cur.next;
        }
        
        cur = head;
        while(cur != null) {
            hashMap.get(cur).random = hashMap.get(cur.random);
            cur = cur.next;
        }
        
        return ret;
    }
}