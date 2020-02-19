class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> tmp = helper(n);
        List<String> ret = new ArrayList<>();
        for (String str : tmp) {
            if (str.charAt(0) != '0' || str.length() == 1) {
                ret.add(str);
            }
        }
        return ret;
    }
    
    private List<String> helper(int n) {
        if (n == 1) {
            return Arrays.asList("0", "1", "8");
        } else if (n == 2) {
            return Arrays.asList("00", "11", "69", "88", "96");
        }
        List<String> subret = helper(n - 2);
        List<String> ret = new ArrayList<>();
        for (String substr : subret) {
            ret.add("0" + substr + "0");
            ret.add("1" + substr + "1");
            ret.add("6" + substr + "9");
            ret.add("8" + substr + "8");
            ret.add("9" + substr + "6");
        }
        return ret;
    }
}