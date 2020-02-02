class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(n, 1, k, new ArrayList<>(), ret);
        return ret; 
    }
    
    private void dfs(int n, int offset, int k, List<Integer> curList, List<List<Integer>> ret) {
        if (k == 0) {
            ret.add(new ArrayList<>(curList));
            return;
        }
        for (int i = offset; i <= n; i++) {
            curList.add(i);
            dfs(n, i + 1, k - 1, curList, ret);
            curList.remove(curList.size() - 1);
        }
    }
}

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();

        backtrack(result, new LinkedList<>(), n, k);
        
        return result; 
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> curList, int n, int k){
        if (k == 0){
            result.add(new LinkedList<>(curList));
            return;
        }
        
        if (n == 0){
            return;
        }
        
        //select n
        curList.add(n);
        backtrack(result, curList, n-1, k-1);
        curList.remove(curList.size()-1);
        //not select n
        backtrack(result, curList, n-1, k);
    }
}
