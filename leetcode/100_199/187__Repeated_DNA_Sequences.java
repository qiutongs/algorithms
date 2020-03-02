// Rolling hash with radix conversion
// Time: O(N)
// Space: O(N)
class Solution {
    private int BASE = 5;
    private int LEN = 10;
    private int H_COEFF = (int)Math.pow(BASE, LEN - 1);
    
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < LEN) {
            return Collections.emptyList();
        }
        HashMap<Integer, Integer> vfMap = new HashMap<>();
        int cur = computeIntialValue(s);
        vfMap.put(cur, 1);
        
        for (int i = LEN; i < s.length(); i++) {
            cur -= toInt(s.charAt(i - LEN)) * H_COEFF;
            cur = cur * BASE + toInt(s.charAt(i));
            vfMap.compute(cur, (k, v) -> v == null ? 1 : v + 1);
        }

        List<String> ret = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : vfMap.entrySet()) {
            if (entry.getValue() > 1) {
                ret.add(toString(entry.getKey()));
            }
        }
        return ret;
    }
    
    private int computeIntialValue(String s) {
        int ret = 0;
        for (int i = 0; i < LEN; i++) {
            ret = ret * BASE + toInt(s.charAt(i));
        }
        return ret;
    }
    
    private String toString(int val) {
        char[] chars = {'A', 'C', 'G', 'T'};
        StringBuilder sb = new StringBuilder();
        while(val > 0) {
            sb.append(chars[val % BASE - 1]);
            val /= BASE;
        }
        sb.reverse();
        return sb.toString();
    }
    
    private int toInt(char c) {
        switch(c) {
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
        }
        return -1;
    }
}