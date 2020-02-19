/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// Reservoir sampling
// Time: O(1) - construct, O(N) - getRandom
// Space: O(1)
// Ref: https://en.wikipedia.org/wiki/Reservoir_sampling
class Solution {
    private final int K = 1;
    private final ListNode head;
    private final Random rand = new Random();
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int ret = head.val;
        ListNode cur = head.next;
        
        int i = 1;
        while(cur != null) {
            int randNum = rand.nextInt(K + i);
            if (randNum < K) {
                ret = cur.val;
            }
            cur = cur.next;
            i++;
        }
        return ret;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */