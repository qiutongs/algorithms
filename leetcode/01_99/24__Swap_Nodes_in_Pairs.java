/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        
        if (head.next == null) {
            return head;
        }
        
        ListNode newHead = head.next;
        ListNode tmpNext = newHead.next;
        newHead.next = head;
        head.next = swapPairs(tmpNext);
        return newHead;
    }
}