/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode cur = head;
        int i = 1;
        
        ListNode beforeM = null;
        ListNode mNode = null;
        ListNode nNode = null;
        ListNode afterN = null;
        
        while(cur != null) {
            if (i == m) {
                beforeM = prev;
                mNode = cur;
            }
            
            if (i == n) {
                nNode = cur;
                afterN = cur.next;
            }
            
            ListNode next = cur.next;
            
            if (i >= m && i <= n) {
                cur.next = prev;
            }
            
            prev = cur;
            cur = next; 
            i++;
        }
        
        if (beforeM != null) {
            beforeM.next = nNode;
        }
        
        if (mNode != null) {
            mNode.next = afterN;
        }

        return dummy.next;
    }
}