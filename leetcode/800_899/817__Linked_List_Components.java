/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int numComponents(ListNode head, int[] G) {
        HashSet<Integer> gSet = new HashSet();
        for (int num : G) {
            gSet.add(num);
        }
        
        boolean inG = false;
        ListNode cur = head;
        int ret = 0;
        while(cur != null) {
            if (gSet.contains(cur.val)) {
                ret++;
                do {
                    cur = cur.next;
                } while(cur != null && gSet.contains(cur.val));
            } else {
                cur = cur.next;
            }
        }
        return ret;
    }
}