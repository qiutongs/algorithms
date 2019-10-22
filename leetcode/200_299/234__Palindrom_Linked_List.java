/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode tail = null;
        
        // odd: 1 -> 2 <- 1
        if (fast.next == null) {
            tail = reverse(slow, slow.next);
        } else { // even: 1 -> 2 -> null  null <- 2 <- 1
            tail = reverse(null, slow.next);
        }
        
        slow.next = null;
        
        while(head != tail) {
            if (head.val != tail.val) {
                return false;
            }
            head = head.next;
            tail = tail.next;
        }
        return true;
    }
    
    private ListNode reverse(ListNode preHead, ListNode head) {
        ListNode prev = preHead, cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}