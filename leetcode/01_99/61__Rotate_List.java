/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        
        int size = getSize(head);
        k = k % size;
        
        if (k == 0) {
            return head;
        }
        
        ListNode cur = head;
        int i = 1;
        
        ListNode beforeK = null, nodeK = null;
        
        while(cur.next != null) {
            if (i == size - k) {
                beforeK = cur;
                nodeK = cur.next;
            }
            
            cur = cur.next;
            i++;
        }
        
        cur.next = head;
        beforeK.next = null; 
        return nodeK;
    }
    
    private int getSize(ListNode head) {
        int ret = 0;
        
        while(head != null) {
            ret++;
            head = head.next;
        }
        
        return ret;
    }
}