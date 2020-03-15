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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<String, List<TreeNode>> strNodesMap = new HashMap<>();
        serialize(root, strNodesMap);
        
        List<TreeNode> ret = new ArrayList<>();
        for (Map.Entry<String, List<TreeNode>> entry : strNodesMap.entrySet()) {
            if (entry.getValue().size() >= 2) {
                ret.add(entry.getValue().get(0));
            }
        }
        return ret;
    }
    
    private String serialize(TreeNode node, HashMap<String, List<TreeNode>> strNodesMap) {
        if (node == null) {
            return "#";
        }
        String left = serialize(node.left, strNodesMap);
        String right = serialize(node.right, strNodesMap); 
        String ret = node.val + "," + left + "," + right;
        strNodesMap.putIfAbsent(ret, new ArrayList<>());
        strNodesMap.get(ret).add(node);
        return ret;
    }
}