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
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy, cur = head;
        while(cur != null) {
            int count = 1;
            while(cur.next != null && cur.val == cur.next.val) {
                count++;
                cur = cur.next;
            }
            
            if (count == 1) {
                prev = cur;
            } else {
                prev.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}