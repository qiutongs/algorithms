class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return helper(root).lca;
    }
    
    private NodeInfo helper(TreeNode node) {
        if (node == null) {
            return new NodeInfo(null, 0);
        }
        if (node.left == null && node.right == null) {
            return new NodeInfo(node, 1);
        }
        NodeInfo leftInfo = helper(node.left);
        NodeInfo rightInfo = helper(node.right);
        if (leftInfo.h < rightInfo.h) {
            return new NodeInfo(rightInfo.lca, rightInfo.h + 1);
        } else if (leftInfo.h > rightInfo.h) {
            return new NodeInfo(leftInfo.lca, leftInfo.h + 1);
        } else {
            return new NodeInfo(node, leftInfo.h + 1);
        }
    }
    
    private class NodeInfo {
        TreeNode lca;
        int h;
        NodeInfo(TreeNode lca, int h) {
            this.lca = lca;
            this.h = h;
        }
    }
}