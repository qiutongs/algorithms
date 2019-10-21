/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        ListNode cur = head;
        ListNode prev = null;
        
        ListNode rightHead = null;
        
        while(cur != null) {
            if (cur.val < x) {
                if (prev != null) {
                    prev.next = cur.next;
                }
                tail.next = cur;
                tail = cur;
            } else {
                if (rightHead == null) {
                    rightHead = cur;
                }
                prev = cur;
            }
            cur = cur.next;
        }
        
        tail.next = rightHead;
        return dummy.next;
    }
}