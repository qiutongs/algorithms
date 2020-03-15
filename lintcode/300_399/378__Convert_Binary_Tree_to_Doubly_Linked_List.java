/**
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * } * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    private DoublyListNode dummyHead = new DoublyListNode(0);
    private DoublyListNode tail = dummyHead;
    
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        inorder(root);
        return dummyHead.next;
    }
    
    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        
        inorder(node.left);
        
        tail.next = new DoublyListNode(node.val);
        tail.next.prev = tail;
        tail = tail.next;
        
        inorder(node.right);
    }
}

public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
            
        }
        return bstToListHelper(root).head;
    }
    
    private Result bstToListHelper(TreeNode node) {
        if (node == null) {
            return null;
        }
        
        Result leftRet = bstToListHelper(node.left);
        Result rightRet = bstToListHelper(node.right);
        
        Result ret = new Result();
        DoublyListNode curNode = new DoublyListNode(node.val);
        
        if (leftRet != null) {
            leftRet.tail.next = curNode;
            curNode.prev = leftRet.tail;
            
            ret.head = leftRet.head;
        } else {
            ret.head = curNode; 
        }
        
        if (rightRet != null) {
            curNode.next = rightRet.head;
            rightRet.head.prev = curNode;
            
            ret.tail = rightRet.tail;
        } else {
            ret.tail = curNode;
        }
        
        return ret;
    }
    
    private class Result {
        DoublyListNode head = null;
        DoublyListNode tail = null;
    }
}