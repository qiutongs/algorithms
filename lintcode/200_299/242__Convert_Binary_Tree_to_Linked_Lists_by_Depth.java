/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param root the root of binary tree
     * @return a lists of linked list
     */
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        List<ListNode> ret = new ArrayList<>();
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(queue.isEmpty() == false) {
            ListNode dummyHead = new ListNode(0);
            ListNode tail = dummyHead;
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                tail.next = new ListNode(node.val);
                tail = tail.next;
                
                if (node.left != null) {
                    queue.add(node.left);
                }
                
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            
            ret.add(dummyHead.next);
        }
        
        return ret;
    }
}