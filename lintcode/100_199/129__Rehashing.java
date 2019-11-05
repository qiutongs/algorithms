/**
 * Definition for ListNode
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
    /**
     * @param hashTable: A list of The first node of linked list
     * @return: A list of The first node of linked list which have twice size
     */    
    public ListNode[] rehashing(ListNode[] hashTable) {
        int capacity = hashTable.length * 2;
        ListNode[] ret = new ListNode[capacity];
        
        for (ListNode node : hashTable) {
            while(node != null) {
                ListNode next = node.next;
                
                int hashCode = (node.val % capacity + capacity) % capacity;
                node.next = ret[hashCode];
                ret[hashCode] = node;
                
                node = next;
            }
        }

        for (int i = 0; i < ret.length; i++) {
            if (ret[i] != null) {
                ret[i] = reverse(ret[i]);
            }
        }
        
        return ret;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null, node = head;
        while(node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
};
