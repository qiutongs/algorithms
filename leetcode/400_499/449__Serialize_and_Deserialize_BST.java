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
        int[] vals = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            vals[i] = Integer.valueOf(strs[i]);
        }
        return deserialize(vals, 0, vals.length - 1);
    }
    
    private TreeNode deserialize(int[] vals, int l, int r) {
        if (l > r) {
            return null;
        }
        TreeNode ret = new TreeNode(vals[l]);
        int i = l;
        while(i + 1 <= r && vals[i + 1] < vals[l]) {
            i++;
        }
        ret.left = deserialize(vals, l + 1, i);
        ret.right = deserialize(vals, i + 1, r);
        return ret;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));