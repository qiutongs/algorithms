// BFS + children terminator
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "#";
        }
        StringBuilder sb = new StringBuilder();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        sb.append(root.val);
        sb.append(',');
        
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            for (Node n : cur.children) {
                sb.append(n.val);
                sb.append(',');
                q.offer(n);
            }
            sb.append("@");
            sb.append(',');
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.equals("#")) {
            return null;
        }
        String[] strs = data.split(",");
        Node ret = new Node(Integer.valueOf(strs[0]));
        int index = 1;
        
        Queue<Node> q = new LinkedList<>();
        q.offer(ret);
        while(q.isEmpty() == false) {
            Node cur = q.poll();
            cur.children = new ArrayList<>();
            for (; strs[index].equals("@") == false; index++) {
                Node child = new Node(Integer.valueOf(strs[index]));
                cur.children.add(child);
                q.offer(child);
            }
            index++;
        }
        return ret;
    }
}

// DFS + []
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }
    
    private void dfs(Node node, StringBuilder sb) {
        sb.append(node.val);
        if (node.children.isEmpty() == false) {
            sb.append('[');
            for (Node child : node.children) {
                dfs(child, sb);
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(']');
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        Node dummy = new Node(0, new ArrayList<>());
        dfs(data, new int[]{0}, dummy);
        return dummy.children.get(0);
    }
    
    private void dfs(String data, int[] index, Node parent) {
        Integer num = null;
        for (; index[0] <= data.length(); index[0]++) {
            char c = index[0] < data.length() ? data.charAt(index[0]) : '\0';
            if (Character.isDigit(c)) {
                num = num == null ? 0 : num;
                num = num * 10 + (c - '0');
            } else {
                Node child = null;
                if (num != null) {
                    child = new Node(num, new ArrayList<>());
                    parent.children.add(child);
                    num = null;
                }
                if (c == '[') {
                    index[0]++;
                    dfs(data, index, child);
                } else if (c == ']') {
                    break;
                }
            }
        }
    }
}

// DFS + child terminator
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }
    
    private void dfs(Node node, StringBuilder sb) {
        sb.append(node.val);
        sb.append(',');
        for (Node child : node.children) {
            dfs(child, sb);
            sb.append(',');
        }
        sb.append('@');
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] strs = data.split(",");
        return dfs(strs, new int[]{0});
    }
    
    private Node dfs(String[] strs, int[] index) {
        if (index[0] == strs.length) {
            return null;
        }
        Node ret = new Node(Integer.valueOf(strs[index[0]]), new ArrayList<>());
        index[0]++;
        for (; strs[index[0]].equals("@") == false; index[0]++) {
            ret.children.add(dfs(strs, index));
        }
        return ret;
    }
}