/*---------------------Linked list as stack--------------------------*/

#define RETURN_SIZE 128
#define STACK_INITIALIZER {.pTop = NULL, .size = 0}

typedef struct Node {
    void * pElement;
    struct Node * pNext;
} Node_t;

typedef struct Stack {
    Node_t * pTop;
    int size;
} Stack_t;

void push(Stack_t * pStack, void * pElement);
void * poll(Stack_t * pStack);

int* preorderTraversal(struct TreeNode* root, int* returnSize) {
    if (root == NULL) {
        *returnSize = 0;
        return NULL;
    }
    Stack_t stack = STACK_INITIALIZER;
    push(&stack, root); 

    int * pRet = malloc(RETURN_SIZE * sizeof(int));
    int size = 0;
    
    while(stack.size > 0){
        struct TreeNode* pTop = poll(&stack);
        pRet[size++] = pTop->val; 
        
        if (pTop->right != NULL){
            push(&stack, pTop->right);
        } 
        if (pTop->left != NULL){
            push(&stack, pTop->left);
        }
    }

    *returnSize = size;
    return pRet;
}

void push(Stack_t * pStack, void * pElement) {
    Node_t * pNode = malloc(sizeof(Node_t));
    pNode->pElement = pElement;
    pNode->pNext = pStack->pTop;
    pStack->pTop = pNode;
    pStack->size++;
}
void * poll(Stack_t * pStack) {
    void * pRet = pStack->pTop->pElement;
    Node_t * pNext = pStack->pTop->pNext;
    free(pStack->pTop);
    pStack->pTop = pNext;
    pStack->size--;
    return pRet;
}

/*--------------------Array as stack---------------------*/
#define RETURN_SIZE 128
#define STACK_INITIALIZER {.pElements = {0}, .size = 0}

typedef struct Stack {
    void * pElements[RETURN_SIZE];
    int size;
} Stack_t;

void push(Stack_t * pStack, void * pElement);
void * poll(Stack_t * pStack);

int* preorderTraversal(struct TreeNode* root, int* returnSize) {
    if (root == NULL) {
        *returnSize = 0;
        return NULL;
    }
    Stack_t stack = STACK_INITIALIZER;
    push(&stack, root); 

    int * pRet = malloc(RETURN_SIZE * sizeof(int));
    int size = 0;
    
    while(stack.size > 0){
        struct TreeNode* pTop = poll(&stack);
        pRet[size++] = pTop->val; 
        
        if (pTop->right != NULL){
            push(&stack, pTop->right);
        } 
        if (pTop->left != NULL){
            push(&stack, pTop->left);
        }
    }

    *returnSize = size;
    return pRet;
}

void push(Stack_t * pStack, void * pElement) {
    pStack->pElements[pStack->size++] = pElement;
}
void * poll(Stack_t * pStack) {
    return pStack->pElements[--pStack->size];
}



