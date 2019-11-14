/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        
        while(q.isEmpty() == false) {
            List<Integer> level = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                level.add(node.val);
                if (node.children != null) {
                    for (Node child : node.children) {
                        q.offer(child);
                    }
                }
            }
            ret.add(level);
        }
        return ret;
    }
}