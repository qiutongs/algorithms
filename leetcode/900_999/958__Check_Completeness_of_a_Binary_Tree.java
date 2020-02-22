// Time: O(N)
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        return helper(root).complete;
    }
    
    private NodeInfo helper(TreeNode node) {
        if (node == null) {
            return new NodeInfo(true, true, 0);
        }
        NodeInfo leftInfo = helper(node.left);
        NodeInfo rightInfo = helper(node.right);
        
        boolean perfect = false;
        boolean complete = false;
        if (leftInfo.height == rightInfo.height) {
            perfect = leftInfo.perfect && rightInfo.perfect;
            complete = leftInfo.perfect && rightInfo.complete;
        } else if (leftInfo.height == rightInfo.height + 1) {
            complete = leftInfo.complete && rightInfo.perfect;
        }
        return new NodeInfo(perfect, complete, 1 + Math.max(leftInfo.height, rightInfo.height));
    }
    
    private class NodeInfo {
        boolean perfect;
        boolean complete;
        int height;
        NodeInfo(boolean perfect, boolean complete, int height) {
            this.perfect = perfect;
            this.complete = complete;
            this.height = height;
        }
    }
}