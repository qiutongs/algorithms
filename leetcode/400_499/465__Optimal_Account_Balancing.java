// Ref: https://leetcode.com/problems/optimal-account-balancing/discuss/95355/11-liner-9ms-DFS-solution-(detailed-explanation)
class Solution {
    public int minTransfers(int[][] transactions) {
        HashMap<Integer, Integer> balances = new HashMap<>();
        for (int[] transaction : transactions) {
            balances.put(transaction[0], balances.getOrDefault(transaction[0], 0) + transaction[2]);
            balances.put(transaction[1], balances.getOrDefault(transaction[1], 0) - transaction[2]);
        }
        return dfs(new ArrayList<>(balances.keySet()), 0, balances);
    }
    
    private int dfs(List<Integer> people, int index, HashMap<Integer, Integer> balances) {
        if (index == people.size()) {
            return 0;
        }
        int id = people.get(index);
        if (balances.get(id) == 0) {
            return dfs(people, index + 1, balances);
        }
        int ret = Integer.MAX_VALUE;
        for (int i = index + 1; i < people.size(); i++) {
            int fId = people.get(i);
            if (balances.get(id) * balances.get(fId) < 0) {
                balances.put(fId, balances.get(fId) + balances.get(id));
                ret = Math.min(ret, 1 + dfs(people, index + 1, balances));
                balances.put(fId, balances.get(fId) - balances.get(id));
            }
        }
        return ret;
    }
}