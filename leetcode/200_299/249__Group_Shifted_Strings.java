class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> vlMap = new HashMap<>();
        for (String str : strings) {
            String shiftStr = shift(str);
            vlMap.putIfAbsent(shiftStr, new ArrayList<>());
            vlMap.get(shiftStr).add(str);
        }
        return new ArrayList<>(vlMap.values());
    }
    
    private String shift(String str) {
        int diff = str.charAt(0) - 'a';
        char[] ret = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ret[i] = (char)((str.charAt(i) - diff + 26) % 26);
        }
        return new String(ret);
    }
}