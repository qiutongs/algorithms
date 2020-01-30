// https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        buildTrie(root, words);
        
        List<String> ret = new ArrayList<>();
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, visited, root, new StringBuilder(), ret);
            }
        }
        return ret;
    }
    
    private void dfs(char[][] board, int x, int y, boolean[][] visited, TrieNode parent, StringBuilder sb, List<String> ret) {
        if (inbound(board, x, y) == false || parent.get(board[x][y]) == null || visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        sb.append(board[x][y]);
        
        try {
            TrieNode node = parent.get(board[x][y]);
            if (node.isKey) {
                ret.add(sb.toString());
                node.isKey = false;
            }

            int[] deltaX = {0, 0, 1, -1};
            int[] deltaY = {1, -1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int x1 = x + deltaX[i];
                int y1 = y + deltaY[i];
                dfs(board, x1, y1, visited, node, sb, ret);
            }
        } finally {
            sb.deleteCharAt(sb.length() - 1);
            visited[x][y] = false;
        }
    }
    
    private boolean inbound(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
    
    private void buildTrie(TrieNode root, String[] words) {
        for(String word : words) {
            insert(root, word);
        }
    }
    
    private void insert(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.addIfAbsent(c);
            cur = cur.get(c);
        }
        cur.isKey = true;
    }
    
    private class TrieNode {
        TrieNode[] childs = new TrieNode[26];
        boolean isKey = false;
        
        void addIfAbsent(char c) {
            if (childs[(int)(c - 'a')] == null) {
                childs[(int)(c - 'a')] = new TrieNode();
            }
        }
        
        TrieNode get(char c) {
            return childs[(int)(c - 'a')];
        }
    }
}

// Raw DFS
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ret = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) {
                ret.add(word);
            }
        }
        return ret;
    }
    
    private boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(visited[i], false);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] board, int x, int y, String word, int wordIndex, boolean[][] visited) {
        if (word.charAt(wordIndex) != board[x][y]) {
            return false;
        }
        
        if (wordIndex == word.length() - 1) {
            return true;
        }

        visited[x][y] = true;
        
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        
        for (int i = 0; i < 4; i++) {
            int nextX = x + deltaX[i];
            int nextY = y + deltaY[i];
            
            if (inBound(board, nextX, nextY) && visited[nextX][nextY] == false) {
                if (dfs(board, nextX, nextY, word, wordIndex + 1, visited)) {
                    return true;
                }
            }
        }
        
        visited[x][y] = false;
        
        return false;
    }
    
    private boolean inBound(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
}