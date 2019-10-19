/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return helper(head);
    }
    
    private TreeNode helper(ListNode node) {
        if (node == null) {
            return null;
        }
        
        if (node.next == null) {
            return new TreeNode(node.val);
        }
        
        ListNode slow = node, fast = node;
        ListNode pre = null;
        
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        TreeNode ret = new TreeNode(slow.val);
        
        ListNode afterMid = slow.next;
        
        if (pre != null) {
            pre.next = null;
        }
        
        ret.left = helper(node);
        ret.right = helper(afterMid);
        return ret;
    }
}