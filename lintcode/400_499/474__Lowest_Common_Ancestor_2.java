/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */


public class Solution {
    /*
     * @param root: The root of the tree
     * @param A: node in the tree
     * @param B: node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        Deque<ParentTreeNode> pathA = new LinkedList<>();
        Deque<ParentTreeNode> pathB = new LinkedList<>();
        
        ParentTreeNode curA = A;
        while(curA != null) {
            pathA.addFirst(curA);
            curA = curA.parent;
        }
        
        ParentTreeNode curB = B;
        while(curB != null) {
            pathB.addFirst(curB);
            curB = curB.parent;
        }
        
        ParentTreeNode ret = null;
        Iterator<ParentTreeNode> iterA = pathA.iterator();
        Iterator<ParentTreeNode> iterB = pathB.iterator();
        
        while(iterA.hasNext() && iterB.hasNext()) {
            curA = iterA.next();
            curB = iterB.next();
            if (curA == curB) {
                ret = curA;
            } else {
                break;
            }
        }
        
        return ret;
    }
}