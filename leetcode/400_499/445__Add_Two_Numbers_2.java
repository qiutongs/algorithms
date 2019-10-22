/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        
        Stack<ListNode> stack1 = createStack(l1);
        Stack<ListNode> stack2 = createStack(l2);
        
        int sum = 0;
        int carry = 0;
        while(stack1.isEmpty() == false || stack2.isEmpty() == false) {
            sum = stack1.isEmpty() ? stack2.pop().val + carry
                : stack2.isEmpty() ? stack1.pop().val + carry
                : stack1.pop().val + stack2.pop().val + carry;
            
            carry = sum / 10;
            
            ListNode n = new ListNode(sum % 10);
            n.next = head;
            head = n;
        }
        
        if (carry > 0) {
            ListNode n = new ListNode(carry);
            n.next = head;
            head = n;
        }
        
        return head;
    }
    
    private Stack<ListNode> createStack(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while(head != null) {
            stack.push(head);
            head = head.next;
        }
        return stack;
    }
}