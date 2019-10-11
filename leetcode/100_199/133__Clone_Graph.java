/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
class Solution1 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        
        //Node ret = new Node(node.val, new LinkedList<>());
        Node ret = new Node();
        ret.val = node.val;
        ret.neighbors =  new LinkedList<>();
        
        HashMap<Node, Node> oldNewMap = new HashMap<>();
        
        oldNewMap.put(node, ret);
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        
        while(queue.isEmpty() == false) {
            Node head = queue.remove();
            Node newHead = oldNewMap.get(head);

            for (Node neighbor : head.neighbors) {
                Node newNeighbor = oldNewMap.get(neighbor);
                
                if (newNeighbor == null) {
                    queue.add(neighbor);
                    
                    newNeighbor = new Node(neighbor.val, new LinkedList<>());
                    oldNewMap.put(neighbor, newNeighbor);
                }
                
                newHead.neighbors.add(newNeighbor);
            }
        }
        
        return ret;
    }
}

class Solution2 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Node ret = new Node(node.val, new LinkedList<>());
        
        HashMap<Node, Node> oldNewMap = new HashMap<>();
        
        oldNewMap.put(node, ret);
        
        dfs(node, oldNewMap);
        
        return ret;
    }
    
    private void dfs(Node node, HashMap<Node, Node> oldNewMap) {
        if (node == null) {
            return;
        }
        
        Node newNode = oldNewMap.get(node);
        
        for (Node neighbor : node.neighbors) {
            Node newNeighbor = oldNewMap.get(neighbor);
            
            if (newNeighbor == null) {
                newNeighbor = new Node(neighbor.val, new LinkedList<>());
                oldNewMap.put(neighbor, newNeighbor);
                
                dfs(neighbor, oldNewMap);
            }
            
            newNode.neighbors.add(newNeighbor);
        }
    }
}

class Solution3 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Node ret = new Node(node.val, new LinkedList<>());
        
        HashMap<Node, Node> oldNewMap = new HashMap<>();
        
        oldNewMap.put(node, ret);
        
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        
        while(stack.isEmpty() == false) {
            Node top = stack.pop();
            Node newTop = oldNewMap.get(top);

            for (Node neighbor : top.neighbors) {
                Node newNeighbor = oldNewMap.get(neighbor);
                
                if (newNeighbor == null) {
                    stack.push(neighbor);
                    
                    newNeighbor = new Node(neighbor.val, new LinkedList<>());
                    oldNewMap.put(neighbor, newNeighbor);
                }
                
                newTop.neighbors.add(newNeighbor);
            }
        }
        
        return ret;
    }
}