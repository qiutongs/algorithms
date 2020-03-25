// DFS + hashtable
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        return dfs(node, new HashMap<>());
    }
    
    private Node dfs(Node node, HashMap<Node, Node> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node ret = new Node(node.val);
        map.put(node, ret);
        for (Node nb : node.neighbors) {
            ret.neighbors.add(dfs(nb, map));
        }
        return ret;
    }
}

// BFS
class Solution {
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

class Solution {
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