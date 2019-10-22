/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode prev = head, cur = head.next;
        
        while(cur != null) {
            if (prev.val > cur.val) {
                prev.next = cur.next;
                head = insert(head, prev, cur);
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        
        return head;
    }
    
    private ListNode insert(ListNode head, ListNode tail, ListNode target) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy, cur = head;
        while(cur != tail.next) {
            if (target.val <= cur.val) {
                target.next = cur;
                prev.next = target;
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        
        return dummy.next;
    }
}