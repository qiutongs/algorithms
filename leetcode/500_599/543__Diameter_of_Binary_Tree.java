// Observation: max path = left_height + right_height of some node
// Time: O(N)
class Solution {
    private int ret = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        getHeight(root);
        return ret;
    }
    
    private int getHeight(TreeNode node){
        if (node == null) {
            return 0;
        }
        int leftH = getHeight(node.left);
        int rightH = getHeight(node.right);
        ret = Math.max(ret, leftH + rightH);
        return 1 + Math.max(leftH, rightH);
    }
}

// Slow
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        int leftToRight = maxPathFrom(root.left) + maxPathFrom(root.right);
        
        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);
        
        return Math.max(Math.max(leftDiameter, rightDiameter), leftToRight);
    }
    
    private int maxPathFrom(TreeNode node){
        if (node == null) return 0;
        
        int left = maxPathFrom(node.left);
        int right = maxPathFrom(node.right);
        
        return Math.max(left+1, right+1);
    }
}