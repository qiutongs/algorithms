/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }

 Ideas:
 - remove duplicated one by one
 - goto next non-duplciated one: delete multiple elements at a time
 */
class Solution1 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode curNode = head.next, preNode = head;

        while(curNode != null) {
            if (curNode.val == preNode.val) {
                preNode.next = curNode.next;
            } else {
                preNode = curNode;
            }

            curNode = curNode.next;
        }

        return head;
    }
}

class Solution2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode cur = head;

        while(cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next; 
            }
        }

        return head;
    }
}
