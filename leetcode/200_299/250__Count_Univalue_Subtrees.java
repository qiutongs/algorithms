class Solution {
    public int countUnivalSubtrees(TreeNode root) {
        return helper(root).count;
    }
    
    private NodeInfo helper(TreeNode node) {
        if (node == null) {
            return new NodeInfo(0, false);
        }
        NodeInfo leftInfo = helper(node.left);
        NodeInfo rightInfo = helper(node.right);
        boolean isUni = true;
        isUni &= node.left == null || (leftInfo.isUni && node.val == node.left.val);
        isUni &= node.right == null || (rightInfo.isUni && node.val == node.right.val);
        return new NodeInfo(leftInfo.count + rightInfo.count + (isUni ? 1 : 0), isUni);
    }
    
    private class NodeInfo {
        int count;
        boolean isUni;
        NodeInfo(int count, boolean isUni) {
            this.count = count;
            this.isUni = isUni;
        }
    }
}