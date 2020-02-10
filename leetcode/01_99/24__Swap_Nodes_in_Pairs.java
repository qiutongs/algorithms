/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// Recursive
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode n2 = head.next;
        ListNode n3 = n2.next;
        n2.next = head;
        head.next = swapPairs(n3);
        return n2;
    }
}

class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode first = head, second = head.next;
        ListNode prev = dummy;
        
        while(first != null && second != null) {
            first.next = second.next;
            second.next = first;
            prev.next = second;

            prev = first;
            first = first.next;
            second = first == null ? null : first.next;
        }
        
        return dummy.next;
    }
}