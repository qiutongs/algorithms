class Solution1 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.isEmpty() || wordList.indexOf(endWord) == -1) {
            return Collections.emptyList();
        }
        
        Map<String, List<String>> graph = buildGraph(beginWord, wordList);
        Map<String, Integer> levels = new HashMap<>();

        bfs(graph, beginWord, endWord, levels);
        
        List<List<String>> ret = new LinkedList<>();
        
        if (levels.containsKey(endWord)) {
            dfs(ret, new ArrayList<>(), graph, beginWord, endWord, levels);
        }

        return ret;
    }
    
    private void bfs(Map<String, List<String>> graph, String beginWord, String endWord, Map<String, Integer> levels) {
        Set<String> visited = new HashSet<>();
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);
        
        int level = 0;
        
        while(queue.isEmpty() == false) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String str = queue.remove();
                
                levels.put(str, level);
                
                if (str.equals(endWord)) {
                    return;
                }
                
                for (String neighbor : graph.get(str)) {
                    if (visited.contains(neighbor) == false) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            
            level++;
        }
    }
    
    void dfs(List<List<String>> ret, 
             List<String> curPath,
             Map<String, List<String>> graph,
             String beginWord, 
             String endWord, 
             Map<String, Integer> levels) {
        if (endWord.equals(beginWord)) {
            List<String> path = new ArrayList<>(curPath);
            path.add(beginWord);
            Collections.reverse(path);
            ret.add(path);
            return;
        }
        
        curPath.add(endWord);
        
        for (String neighbor : graph.get(endWord)) {
            Integer level = levels.get(neighbor);
            
            if (level != null && level == levels.get(endWord) - 1) {
                dfs(ret, curPath, graph, beginWord, neighbor, levels);
            }
        }
        
        curPath.remove(curPath.size() - 1);
    }
    
    private Map<String, List<String>> buildGraph(String beginWord, List<String> wordList) {
        Map<String, List<String>> ret = new HashMap<>();
        
        for (String word : wordList) {
            ret.put(word, new LinkedList<>());
        }
        
        for (String word1 : wordList) {
            for (String word2: wordList) {
                if (word1 != word2) {
                    if (differByOne(word1, word2)) {
                        ret.get(word1).add(word2);
                    }
                }
            }
        }
        
        if (ret.containsKey(beginWord) == false) {
            ret.put(beginWord, new LinkedList<>());
            
            for (String word : wordList) {
                if (differByOne(word, beginWord)) {
                    ret.get(beginWord).add(word);
                    ret.get(word).add(beginWord);
                }
            }
        }

        return ret;
    }
    
    boolean differByOne(String s1, String s2) {
        int diff = 0;
        
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                
                if (diff > 1) {
                    break;
                }
            }
        }
        
        return diff == 1;
    }
}

class Solution2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.isEmpty() || wordList.indexOf(endWord) == -1) {
            return Collections.emptyList();
        }
        
        Map<String, List<String>> graph = buildGraph(beginWord, wordList);
        Map<String, Integer> levels = new HashMap<>();

        bfs(graph, beginWord, endWord, levels);
        
        List<List<String>> ret = new LinkedList<>();
        List<String> curPath = new ArrayList<>();
        curPath.add(beginWord);
        
        if (levels.containsKey(endWord)) {
            dfs(ret, curPath, graph, endWord, levels);
        }

        return ret;
    }
    
    private void bfs(Map<String, List<String>> graph, String beginWord, String endWord, Map<String, Integer> levels) {
        Set<String> visited = new HashSet<>();
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);
        
        int level = 0;
        
        while(queue.isEmpty() == false) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String str = queue.remove();
                
                levels.put(str, level);
                
                if (str.equals(endWord)) {
                    return;
                }
                
                for (String neighbor : graph.get(str)) {
                    if (visited.contains(neighbor) == false) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            
            level++;
        }
    }
    
    void dfs(List<List<String>> ret, 
             List<String> curPath,
             Map<String, List<String>> graph,
             String endWord, 
             Map<String, Integer> levels) {
        String endOfPath = curPath.get(curPath.size() - 1);
        
        if (levels.get(endOfPath) == levels.get(endWord)) {
            if (endWord.equals(endOfPath)) {
                ret.add(new ArrayList<>(curPath));
                return;
            } else {
                return;
            }
        }
        
        for (String neighbor : graph.get(endOfPath)) {
            Integer level = levels.get(neighbor);
            
            if (level != null && level == levels.get(endOfPath) + 1) {
                curPath.add(neighbor);
                dfs(ret, curPath, graph, endWord, levels);
                curPath.remove(curPath.size() - 1);
            }
        }
    }
    
    private Map<String, List<String>> buildGraph(String beginWord, List<String> wordList) {
        Map<String, List<String>> ret = new HashMap<>();
        
        for (String word : wordList) {
            ret.put(word, new LinkedList<>());
        }
        
        for (String word1 : wordList) {
            for (String word2: wordList) {
                if (word1 != word2) {
                    if (differByOne(word1, word2)) {
                        ret.get(word1).add(word2);
                    }
                }
            }
        }
        
        if (ret.containsKey(beginWord) == false) {
            ret.put(beginWord, new LinkedList<>());
            
            for (String word : wordList) {
                if (differByOne(word, beginWord)) {
                    ret.get(beginWord).add(word);
                    ret.get(word).add(beginWord);
                }
            }
        }

        return ret;
    }
    
    boolean differByOne(String s1, String s2) {
        int diff = 0;
        
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                
                if (diff > 1) {
                    break;
                }
            }
        }
        
        return diff == 1;
    }
}

// Incorrect solution: only return one path, not all
class Solution3 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.isEmpty() || wordList.indexOf(endWord) == -1) {
            return Collections.emptyList();
        }
        
        Map<String, List<String>> graph = buildGraph(beginWord, wordList);
        Map<String, String> parents = new HashMap<>();
        Set<String> visited = new HashSet<>();
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);
        parents.put(beginWord, null);
        
        List<List<String>> ret = new LinkedList<>();
        
        while(queue.isEmpty() == false) {
            int size = queue.size();
            boolean found = false;
            
            for (int i = 0; i < size; i++) {
                String str = queue.remove();
                
                if (str.equals(endWord)) {
                    found = true;
                    ret.add(buildPath(parents, endWord));
                }
                
                for (String neighbor : graph.get(str)) {
                    if (visited.contains(neighbor) == false) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                        parents.put(neighbor, str);
                    }
                }
            }
            
            if (found) {
                break;
            }
        }
        
        return ret;
    }
    
    private List<String> buildPath(Map<String, String> parents, String endWord) {
        List<String> ret = new LinkedList<>();
        
        String str = endWord;
        
        while(str != null) {
            ret.add(0, str);
            str = parents.get(str);
        }
        
        return ret;
    }
    
    private Map<String, List<String>> buildGraph(String beginWord, List<String> wordList) {
        Map<String, List<String>> ret = new HashMap<>();
        
        for (String word : wordList) {
            ret.put(word, new LinkedList<>());
        }
        
        for (String word1 : wordList) {
            for (String word2: wordList) {
                if (word1 != word2) {
                    if (differByOne(word1, word2)) {
                        ret.get(word1).add(word2);
                    }
                }
            }
        }
        
        if (ret.containsKey(beginWord) == false) {
            ret.put(beginWord, new LinkedList<>());
            
            for (String word : wordList) {
                if (differByOne(word, beginWord)) {
                 ret.get(beginWord).add(word);
                }
            }
        }

        return ret;
    }
    
    boolean differByOne(String s1, String s2) {
        int diff = 0;
        
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                
                if (diff > 1) {
                    break;
                }
            }
        }
        
        return diff == 1;
    }
}