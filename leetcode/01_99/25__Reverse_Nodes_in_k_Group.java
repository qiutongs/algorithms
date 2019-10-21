/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        
        while(cur != null) {
            ListNode nk = getKthNode(prev, k);
            
            if (nk == null) {
                break;
            }
            
            ListNode nkNext = nk.next;
            
            reverse(cur, nkNext);
            
            prev.next = nk;
            cur.next = nkNext;
            
            prev = cur;
            cur = nkNext;
        }
        
        return dummy.next;
    }
    
    private ListNode getKthNode(ListNode prev, int k) {
        ListNode kIter = prev;
            
        for (int i = 0; i < k; i++) {
            if (kIter != null) {
                kIter = kIter.next;
            } else {
                return null;
            }
        }
        return kIter;
    }
    
    private void reverse(ListNode head, ListNode end) {
        ListNode prev = head;
        ListNode cur = head.next;
        
        while(cur != end) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
    }
}