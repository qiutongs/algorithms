/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode oddTail = head;
        ListNode evenHead = head.next;
        
        ListNode prev = head.next, cur = head.next.next;
        
        while(cur != null) {
            prev.next = cur.next;
            oddTail.next = cur;
            oddTail = cur;
            
            prev = cur.next;
            cur = cur.next == null ? null : cur.next.next;
        }
        
        oddTail.next = evenHead;
            
        return head;
    }
}