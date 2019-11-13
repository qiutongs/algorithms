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

        int carry = 0;
        while(stack1.isEmpty() == false || stack2.isEmpty() == false) {
            int sum = carry;
            sum = stack1.isEmpty() == false ? sum + stack1.pop().val : sum;
            sum = stack2.isEmpty() == false ? sum + stack2.pop().val : sum;
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