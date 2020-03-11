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
        ListNode dummy1 = new ListNode(0);
        ListNode tail1 = dummy1;
        ListNode dummy2 = new ListNode(0);
        ListNode tail2 = dummy2;
        
        ListNode cur = head;
        while(cur != null) {
            if (cur.val < x) {
                tail1.next = cur;
                tail1 = cur;
            } else {
                tail2.next = cur;
                tail2 = cur;
            }
            cur = cur.next;
        }
        tail1.next = dummy2.next;
        tail2.next = null;
        return dummy1.next;
    }
}