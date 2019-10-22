/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode cur = head;

        while(cur != null) {
            ListNode n = cur.next;
            while(n != null && n.val == cur.val) {
                n = n.next;
            }
            
            if (n == cur.next) {
                prev = cur;
                cur = cur.next;
            } else {
                prev.next = n;
                cur = n;
            }
        }

        return dummy.next;
    }
}
