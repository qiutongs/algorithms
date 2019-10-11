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
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        int nullNode = 0;
        
        while(queue.isEmpty() == false) {
            TreeNode node = queue.remove();
            
            if (node != null) {
                for (int i = 1; i <= nullNode; i++) {
                    sb.append("null ");
                }
                nullNode = 0;
                
                sb.append(node.val);
                sb.append(" ");
                
                queue.add(node.left);
                queue.add(node.right);
            } else {
                nullNode++;
            }
        }
        
        return sb.toString().trim();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        TreeNode[] treeNodes = buildNodes(data);
        
        Queue<TreeNode> queue = new LinkedList<>();
        int index = 1;
        
        queue.add(treeNodes[0]);
        
        while(queue.isEmpty() == false) {
            TreeNode node = queue.remove();
                
            if (node != null) {
                if (index < treeNodes.length) {
                    node.left = treeNodes[index++];
                    queue.add(node.left);
                }
                    
                if (index < treeNodes.length) {
                    node.right = treeNodes[index++];
                    queue.add(node.right);
                }
            }
        }
        
        return treeNodes[0];
    }
    
    private TreeNode[] buildNodes(String data) {
        String[] nodes = data.split("\\s");

        TreeNode[] treeNodes = new TreeNode[nodes.length];
        
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].equals("null")) {
                treeNodes[i] = null;
            } else {
                treeNodes[i] = new TreeNode(Integer.valueOf(nodes[i]));
            }
        }
        
        return treeNodes;
    }

    // Decodes your encoded data to tree.
    // !! Incorrect: [5,2,3,null,null,2,4,3,1]
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        
        String[] nodes = data.split("\\s");
        TreeNode[] treeNodes = new TreeNode[nodes.length];
        
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].equals("null")) {
                treeNodes[i] = null;
            } else {
                treeNodes[i] = new TreeNode(Integer.valueOf(nodes[i]));
            }
        }
        
        for (int i = 0; i < treeNodes.length; i++) {
            if (i * 2 + 1 < treeNodes.length) {
                treeNodes[i].left = treeNodes[i * 2 + 1];
            }
            
            if (i * 2 + 2 < treeNodes.length) {
                treeNodes[i].right = treeNodes[i * 2 + 2];
            }
        }
        
        return treeNodes[0];
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));