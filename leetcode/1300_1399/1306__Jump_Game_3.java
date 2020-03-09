class Solution {
    public boolean canReach(int[] arr, int start) {
        return dfs(arr, start, new boolean[arr.length]);
    }
    
    private boolean dfs(int[] arr, int cur, boolean[] visited) {
        visited[cur] = true;
        if (arr[cur] == 0) {
            return true;
        }
        for (int i : new int[]{cur - arr[cur], cur + arr[cur]}) {
            if (i < 0 || i >= arr.length || visited[i]) {
                continue;
            }
            if (dfs(arr, i, visited)) {
                return true;
            }
        }
        return false;
    }
}