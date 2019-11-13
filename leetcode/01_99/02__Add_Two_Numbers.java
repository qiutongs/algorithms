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
            int sum = carry;
            sum = p1 != null ? sum + p1.val : sum;
            sum = p2 != null ? sum + p2.val : sum;
            cur.next = new ListNode(sum % 10);
            carry = sum / 10;
            
            cur = cur.next;
            p1 = p1 != null ? p1.next : p1;
            p2 = p2 != null ? p2.next : p2;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }   
        return dummy.next;
    }
}