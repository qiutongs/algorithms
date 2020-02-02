/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.
*/
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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> left = new ArrayList<>();
        List<List<Integer>> right = new ArrayList<>();
        bfs(root, left, right);
        
        List<List<Integer>> ret = new ArrayList<>();
        Collections.reverse(left);
        ret.addAll(left);
        ret.addAll(right);
        return ret;
    }
    
    private void bfs(TreeNode root, List<List<Integer>> left, List<List<Integer>> right) {
        if (root == null) {
            return;
        }
        Queue<NodeInfo> q = new LinkedList<>();
        q.offer(new NodeInfo(root, 0));
        
        while(q.isEmpty() == false) {
            NodeInfo info = q.poll();
            
            List<List<Integer>> output = info.offset <= 0 ? left : right;
            int index = info.offset <= 0 ? -info.offset : info.offset - 1;
            if (index >= output.size()) {
                output.add(new ArrayList<>());
            }
            output.get(index).add(info.node.val);
            
            if (info.node.left != null) {
                q.offer(new NodeInfo(info.node.left, info.offset - 1));
            }
            if (info.node.right != null) {
                q.offer(new NodeInfo(info.node.right, info.offset + 1));
            }
        }
    }
    
    private class NodeInfo {
        TreeNode node;
        int offset;
        NodeInfo(TreeNode node, int offset) {
            this.node = node;
            this.offset = offset;
        }
    }
}

// Wrong: the order is wrong because the algorithm doesn't consider depth
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> left = new ArrayList<>();
        List<List<Integer>> right = new ArrayList<>();
        traverse(root, 0, left, right);
        
        List<List<Integer>> ret = new ArrayList<>();
        Collections.reverse(left);
        ret.addAll(left);
        ret.addAll(right);
        return ret;
    }
    
    private void traverse(TreeNode node, int offset, List<List<Integer>> left, List<List<Integer>> right) {
        if (node == null) {
            return;
        }
        List<List<Integer>> output = offset <= 0 ? left : right;
        int index = offset <= 0 ? -offset : offset - 1;
        if (index >= output.size()) {
            output.add(new ArrayList<>());
        }
        output.get(index).add(node.val);
        
        traverse(node.left, offset - 1, left, right);
        traverse(node.right, offset + 1, left, right);
    }
}