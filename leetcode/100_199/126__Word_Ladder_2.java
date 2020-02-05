// DFS end to begin
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> levels = new HashMap<>();
        buildGraph(beginWord, wordList, graph, levels);

        if (bfs(graph, beginWord, endWord, levels) > 0) {
            List<List<String>> ret = new ArrayList<>();
            List<String> curList = new ArrayList<>();
            curList.add(endWord);
            dfs(endWord, curList, ret, graph, beginWord, levels);
            return ret;
        } else {
            return Collections.emptyList();
        }
    }
    
    private int bfs(Map<String, List<String>> graph, String beginWord, String endWord, Map<String, Integer> levels) {
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        visited.add(beginWord);
        
        int level = 0;
        while (q.isEmpty() == false) {
            level++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String node = q.poll();
                levels.put(node, level);
                if (node.equals(endWord)) {
                    return level;
                }
                for (String nb : graph.get(node)) {
                    if (visited.contains(nb) == false) {
                        q.offer(nb);
                        visited.add(nb);
                    }
                }
            }
        }
        return 0;
    }
    
    private void dfs(String curWord, List<String> curList, List<List<String>> ret, Map<String, List<String>> graph, String target, Map<String, Integer> levels) {
        if (curWord.equals(target)) {
            List<String> tmp = new ArrayList<>(curList);
            Collections.reverse(tmp);
            ret.add(tmp);
            return;
        }

        for (String nb : graph.get(curWord)) {
            if (levels.get(nb) == levels.get(curWord) - 1) {
                curList.add(nb);
                dfs(nb, curList, ret, graph, target, levels);
                curList.remove(curList.size() - 1);
            }
        }
    }
    
    private void buildGraph(String beginWord, List<String> wordList, Map<String, List<String>> graph, Map<String, Integer> levels) {
        wordList.add(beginWord);
        
        for (String word : wordList) {
            graph.put(word, new ArrayList<>());
            levels.put(word, -1);
        }
        
        for (String word : wordList) {
            char[] cArr = word.toCharArray();
            for (int i = 0; i < cArr.length; i++) {
                char cur = cArr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != cur) {
                        cArr[i] = c;
                        String nb = new String(cArr);
                        if (graph.containsKey(nb)) {
                            graph.get(word).add(nb);
                        }
                    }
                }
                cArr[i] = cur;
            }
        }
        wordList.remove(wordList.size() - 1);
    }
}

// begin to end
class Solution {
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

//  wrong: only return one path, not all
class Solution {
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