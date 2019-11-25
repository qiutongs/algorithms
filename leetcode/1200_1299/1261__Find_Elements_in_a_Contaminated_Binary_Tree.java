/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class FindElements {
    private final TreeNode root;
    
    public FindElements(TreeNode root) {
        this.root = root;
        if (root != null) {
            root.val = 0;
            recover(root.left, root);
            recover(root.right, root);
        }
    }
    
    private void recover(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        if (node == parent.left) {
            node.val = parent.val * 2 + 1;
        } else {
            node.val = parent.val * 2 + 2;
        }
        recover(node.left, node);
        recover(node.right, node);
    }
    
    public boolean find(int target) {
        if (target < 0) {
            return false;
        }
        return dfs(target) != null;
    }
    
    private TreeNode dfs(int target) {
        if (target == 0) {
            return root;
        }
        TreeNode parent = dfs((target - 1) / 2);
        if (parent != null) {
            if (target % 2 == 1 && parent.left != null) {
                return parent.left;
            }
            if (target % 2 == 0 && parent.right != null) {
                return parent.right;
            }
        }
        return null;
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */