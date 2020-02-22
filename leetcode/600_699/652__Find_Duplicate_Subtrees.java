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
        HashMap<TreeNode, String> nodeSerialMap = new HashMap<>();
        serialize(root, nodeSerialMap);
        
        HashMap<String, List<TreeNode>> serialNodeListMap = new HashMap<>();
        for (Map.Entry<TreeNode, String> entry : nodeSerialMap.entrySet()) {
            serialNodeListMap.putIfAbsent(entry.getValue(), new ArrayList<>());
            serialNodeListMap.get(entry.getValue()).add(entry.getKey());
        }
        
        List<TreeNode> ret = new ArrayList<>();
        for (Map.Entry<String, List<TreeNode>> entry : serialNodeListMap.entrySet()) {
            if (entry.getValue().size() >= 2) {
                ret.add(entry.getValue().get(0));
            }
        }
        return ret;
    }
    
    private String serialize(TreeNode node, HashMap<TreeNode, String> nodeSerialMap) {
        if (node == null) {
            return "#";
        }
        String left = serialize(node.left, nodeSerialMap);
        String right = serialize(node.right, nodeSerialMap); 
        String ret = node.val + "," + left + "," + right;
        nodeSerialMap.put(node, ret);
        return ret;
    }
}