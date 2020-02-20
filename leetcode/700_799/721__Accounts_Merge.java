// Union-Find
// Time: O()
class Solution {
    private Map<String, String> parent = new HashMap<>();
    private Map<String, String> emailNameMap = new HashMap<>();
    private Map<String, List<String>> mergedAccounts = new HashMap<>();
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Initialize UF; map email to name
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                parent.put(account.get(i), account.get(i));
                emailNameMap.put(account.get(i), account.get(0));
            }
        }
        
        // Union every pair of emails
        for (List<String> account : accounts) {
            for (int i = 2; i < account.size(); i++) {
                union(account.get(i - 1), account.get(i));
            }
        }

        for (String email : parent.keySet()) {
            String root = find(email);
            mergedAccounts.putIfAbsent(root, new ArrayList<>());
            mergedAccounts.get(root).add(email);
        }
        
        List<List<String>> ret = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : mergedAccounts.entrySet()) {
            List<String> account = new ArrayList<>();
            account.add(emailNameMap.get(entry.getKey()));
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            account.addAll(emails);
            ret.add(account);
        }
        return ret;
    }
    
    private void union(String p, String q) {
        String rootP = find(p), rootQ = find(q);
        parent.put(rootP, rootQ);
    }
    
    private String find(String x) {
        while(parent.get(x).equals(x) == false) {
            parent.put(parent.get(x), parent.get(parent.get(x)));
            x = parent.get(x);
        }
        return x;
    }
}