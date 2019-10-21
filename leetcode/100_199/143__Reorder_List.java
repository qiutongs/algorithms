/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        
        ListNode rightHead = split(head);
        rightHead = reverse(rightHead);
        merge(head, rightHead);
    }
    
    private ListNode split(ListNode head) {
        ListNode slow = head, fast = head;
        
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode ret = slow.next;
        slow.next = null;
        return ret;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        
        return prev;
    }
    
    private void merge(ListNode leftHead, ListNode rightHead) {
        ListNode p1 = leftHead, p2 = rightHead;
        
        while(p1 != null && p2 != null) {
            ListNode p1Next = p1.next;
            ListNode p2Next = p2.next;
            
            p1.next = p2;
            p2.next = p1Next;
            
            p1 = p1Next;
            p2 = p2Next;
        }
    }
}