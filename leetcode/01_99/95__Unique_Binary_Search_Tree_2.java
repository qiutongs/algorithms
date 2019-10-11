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
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return Collections.emptyList();
        }
        
        return helper(1, n);
    }
    
    private List<TreeNode> helper(int l, int r) {
        List<TreeNode> ret = new LinkedList<>();
        
        if (l > r) {
            ret.add(null);
            return ret;
        }

        for (int i = l; i <= r; i++) {
            List<TreeNode> leftTrees = helper(l, i - 1);
            List<TreeNode> rightTrees = helper(i + 1, r);
            
            for (TreeNode leftChild : leftTrees) {
                for (TreeNode rightChild : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftChild;
                    root.right = rightChild;
                    ret.add(root);
                }
            }
        }
        
        return ret;
    }
}