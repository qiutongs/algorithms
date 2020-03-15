/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }
    
    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.val);
        sb.append(',');
        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] strs = data.split(",");
        return deserialize(strs, new int[]{0}, Integer.MAX_VALUE);
    }
    
    private TreeNode deserialize(String[] strs, int[] index, int max) {
        if (index[0] == strs.length || Integer.valueOf(strs[index[0]]) > max) {
            return null;
        }
        TreeNode ret = new TreeNode(Integer.valueOf(strs[index[0]]));
        index[0]++;
        ret.left = deserialize(strs, index, ret.val);
        ret.right = deserialize(strs, index, max);
        return ret;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));