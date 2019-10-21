/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        ListNode p1 = l1, p2 = l2;
        
        int carry = 0;
        
        while(p1 != null || p2 != null) {
            int sum = p1 == null ? p2.val + carry 
                : p2 == null ? p1.val + carry 
                : p1.val + p2.val + carry;
            
            carry = sum / 10;
            
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            
            if (p1 != null) {
                p1 = p1.next;
            }
            
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
            
        return dummy.next;
    }
}