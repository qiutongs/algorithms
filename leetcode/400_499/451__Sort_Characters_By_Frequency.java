class Solution {
    public String frequencySort(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        HashMap<Character, Integer> cfMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            cfMap.compute(s.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }
        Data[] datas = new Data[cfMap.size()];
        int idx = 0;
        for (Map.Entry<Character, Integer> entry : cfMap.entrySet()) {
            datas[idx++] = new Data(entry.getKey(), entry.getValue());
        }
        
        datas = countingSort(datas, s.length());
        return toString(datas, s.length());
    }
    
    private Data[] countingSort(Data[] datas, int len) {
        Data[] ret = new Data[datas.length];
        int[] count = new int[len + 1];
        for (Data data : datas) {
            count[data.f]++;
        }
        for (int i = count.length - 2; i >= 0; i--) {
            count[i] += count[i + 1];
        }
        for (Data data : datas) {
            ret[count[data.f] - 1] = data;
            count[data.f]--;
        }
        return ret;
    }
    
    private String toString(Data[] datas, int len) {
        char[] ret = new char[len];
        int idx = 0;
        for (Data data : datas) {
            while(data.f > 0) {
                ret[idx++] = data.c;
                data.f--;
            }
        }
        return new String(ret);
    }
    
    private class Data {
        char c;
        int f;
        Data(char c, int f) {
            this.c = c;
            this.f = f;
        }
    }
}