/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution1 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Initialize a dummy head for convinience.
        // The real head will be "dummyHead.next".
        ListNode dummyHead = new ListNode(0), tail = dummyHead;

        // Choose the smaller of element of two lists as next one.
        while(l1 != null && l2 != null) {
            ListNode nextNode = null;
            if (l1.val <= l2.val) {
                nextNode = l1;
                l1 = l1.next;
            } else {
                nextNode = l2;
                l2 = l2.next;
            }

            tail.next = nextNode;
            tail = nextNode;
        }

        // Handle left over list.
        ListNode leftNode = l1 == null ? l2 : l1;

        while(leftNode != null) {
            tail.next = leftNode;
            tail = leftNode;
            leftNode = leftNode.next;
        }

        ListNode head = dummyHead.next;
        dummyHead = null;
        return head;
    }
}

class Solution2 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        
        if (l2 == null) {
            return l1;
        }
        
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}