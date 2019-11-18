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

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/366036/java-preorder
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
            sb.append("#");
            sb.append(",");
            return;
        }
        sb.append(node.val);
        sb.append(",");
        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        Integer[] preVals = toIntArray(data.split(","));
        int[] index = { 0 };
        return construct(preVals, index);
    }
    
    private TreeNode construct(Integer[] preVals, int[] index) {
        if (preVals[index[0]] == null) {
            index[0]++;
            return null;
        }
        TreeNode ret = new TreeNode(preVals[index[0]]);
        index[0]++;
        ret.left = construct(preVals, index);
        ret.right = construct(preVals, index);
        return ret;
    }
    
    private Integer[] toIntArray(String[] strArr) {
        Integer[] ret = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            ret[i] = strArr[i].equals("#") ? null : Integer.valueOf(strArr[i]);
        }
        return ret;
    }
}

// Wrong: cannnot handle duplicate values
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb1 = new StringBuilder();
        preorder(root, sb1);
        StringBuilder sb2 = new StringBuilder();
        inorder(root, sb2);
        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
        return sb1.toString() + " " + sb2.toString();
    }
    
    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.val);
        sb.append(",");
        preorder(node.left, sb);
        preorder(node.right, sb);
    }
    
    private void inorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        inorder(node.left, sb);
        sb.append(node.val);
        sb.append(",");
        inorder(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] orders = data.split(" ");
        int[] preVals = toIntArray(orders[0].split(","));
        int[] inVals = toIntArray(orders[1].split(","));
        return construct(preVals, 0, preVals.length - 1, inVals, 0, inVals.length - 1);
    }
    
    private TreeNode construct(int[] preVals, int preL, int preR, int[] inVals, int inL, int inR) {
        if (preL > preR) {
            return null;
        }
        TreeNode ret = new TreeNode(preVals[preL]);
        int retIndex = inL;
        while(inVals[retIndex] != preVals[preL]) {
            retIndex++;
        }
        ret.left = construct(preVals, preL + 1, preL + retIndex - inL, inVals, inL, retIndex - 1);
        ret.right = construct(preVals, preL + retIndex - inL + 1, preR, inVals, retIndex + 1, inR);
        return ret;
    }
    
    private int[] toIntArray(String[] strArr) {
        int[] ret = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            ret[i] = Integer.valueOf(strArr[i]);
        }
        return ret;
    }
}