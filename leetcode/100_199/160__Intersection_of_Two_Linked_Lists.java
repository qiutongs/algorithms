/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        int sizeA = getSize(headA);
        int sizeB = getSize(headB);
        
        ListNode iA = headA, iB = headB;
        
        for (int i = 0; i < Math.abs(sizeA - sizeB); i++) {
            if (sizeA > sizeB) {
                iA = iA.next;
            } else {
                iB = iB.next;
            }
        }
        
        while(iA != iB) {
            iA = iA.next;
            iB = iB.next;
        }
        
        return iA;
    }
    
    private int getSize(ListNode head) {
        int ret = 0;
        while(head != null) {
            head = head.next;
            ret++;
        }
        return ret;
    }
}