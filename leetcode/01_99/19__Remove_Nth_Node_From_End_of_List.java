/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode slow = dummy, fast = dummy;
        
        for (int i = 1; i <= n; i++) {
            fast = fast.next;
        }
        
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        slow.next = slow.next.next;
        
        return dummy.next;
    }
}

class Solution2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        helper(dummy, head, n);
        
        return dummy.next;
    }
    
    // return id of 'node'; if it is n, it will be removed
    private int helper(ListNode prev, ListNode node, int n) {
        if (node == null) {
            return 0;
        }
        
        int nextId = helper(node, node.next, n);
        
        if (nextId + 1 == n) {
            prev.next = node.next;
        }

        return nextId + 1;
    }
}