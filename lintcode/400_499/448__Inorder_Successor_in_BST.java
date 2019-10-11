/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


public class Solution1 {
    TreeNode lastNode = null;
    TreeNode successor = null;
    
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root, p);
        return successor;
    }
    
    public void inorder(TreeNode node, TreeNode p) {
        if (node == null) {
            return;
        }
        
        inorder(node.left, p);
        
        if (lastNode == p) {
            successor = node;
        }
        
        lastNode = node;
        
        inorder(node.right, p);
    }
}

public class Solution2 {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        
        TreeNode curNode = root;
        TreeNode lastNode = null;
        Stack<TreeNode> stack = new Stack<>();
        
        while(curNode != null || stack.empty() == false) {
            while(curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            
            TreeNode topNode = stack.pop();
            
            if (lastNode == p) {
                return topNode;
            }
            
            lastNode = topNode;
            
            curNode = topNode.right;
        }
        
        return null;
    }
}