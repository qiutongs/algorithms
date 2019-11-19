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
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, cur = head;
        while (cur != null) {
            ListNode nk = getKth(cur, k);
            if (nk == null) {
                break;
            }
            ListNode nk1 = nk.next;
            reverse(cur, nk);
            prev.next = nk;
            cur.next = nk1;
            
            prev = cur;
            cur = nk1;
        }
        return dummy.next;
    }
    
    private ListNode getKth(ListNode node, int k) {
        ListNode ret = node;
        for (int i = 0; i < k - 1; i++) {
            if (ret == null) {
                return null;
            } else {
                ret = ret.next;
            }
        }
        return ret;
    }
    
    private void reverse(ListNode start, ListNode end) {
        ListNode prev = null;
        ListNode cur = start;
        ListNode last = end.next;
        while(cur != last) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
    }
 }