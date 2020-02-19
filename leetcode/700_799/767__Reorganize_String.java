// Sort + Greedy arrangement
// Time: O(N)
// Space: O(N)
class Solution {
    public String reorganizeString(String S) {
        if (S.isEmpty()) {
            return "";
        }
        int len = S.length();
        
        Data[] datas = new Data[26];
        for (int i = 0; i < 26; i++) {
            datas[i] = new Data(i);
        }

        for (int i = 0; i < len; i++) {
            datas[S.charAt(i) - 'a'].freq++;
        }
        
        Arrays.sort(datas, (d1, d2) -> d2.freq - d1.freq);
        
        if (datas[0].freq > (len + 1) / 2) {
            return "";
        }
        return output(datas, len);
    }
    
    private String output(Data[] datas, int total) {
        int n = datas[0].freq;
        StringBuilder[] groups = new StringBuilder[n];
        for (int i = 0; i < n; i++) {
            groups[i] = new StringBuilder();
            groups[i].append(datas[0].c);
        }
        
        int groupIdx = 0;
        for (int i = 1; i < datas.length; i++) {
            while(datas[i].freq > 0) {
                groups[groupIdx].append(datas[i].c);
                datas[i].freq--;
                
                groupIdx++;
                if (groupIdx == n) {
                    groupIdx = 0;
                }
            }
        }
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < n; i++) {
            ret.append(groups[i].toString());
        }
        return ret.toString();
    }
    
    private class Data {
        char c;
        int freq;
        Data(int idx) {
            this.c = (char)(idx + 'a');
            this.freq = 0;
        }
    }
}