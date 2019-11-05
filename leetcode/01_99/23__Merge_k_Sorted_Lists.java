/* 
  Direct k-way merge
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution1 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        PriorityQueue<ListNode> heap = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        
        for (ListNode head : lists) {
            if (head != null) {
                heap.add(head);
            }
        }
        
        while(heap.isEmpty() == false) {
            ListNode minNode = heap.remove();
            if (minNode.next != null) {
                heap.add(minNode.next);
            }
            tail.next = minNode;
            tail = tail.next;
        }
        
        return dummy.next;
    }
}

/*
 * Iterative 2-way merge
 */
class Solution2 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }
    
    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        
        int mid = (l + r) / 2;
        ListNode left = merge(lists, l, mid);
        ListNode right = merge(lists, mid + 1, r);
        return merge(left, right);
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}

