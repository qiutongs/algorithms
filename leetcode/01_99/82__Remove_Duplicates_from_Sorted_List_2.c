/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

typedef struct ListNode ListNode_t;

struct ListNode* deleteDuplicates(struct ListNode* head){
    ListNode_t dummy = {0, head};
    
    ListNode_t * prev = &dummy;
    ListNode_t * cur = head;
    while(cur != NULL) {
        int count = 1;
        while(cur->next != NULL && cur->val == cur->next->val) {
            count++;
            cur = cur->next;
        }
        if (count == 1) {
            prev = cur;
        } else {
            prev->next = cur->next;
        }
        cur = cur->next;
    }
    return dummy.next;
}

