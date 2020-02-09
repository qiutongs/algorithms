class Solution {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 1) {
            return s;
        }
        List<StringBuilder> sbList = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            sbList.add(new StringBuilder());
        }
        
        int groupSize = numRows * 2 - 2;
        for (int i = 0; i < s.length(); i++) {
            int offset = i % groupSize;
            if (offset < numRows) {
                sbList.get(offset).append(s.charAt(i));
            } else {
                sbList.get(groupSize - offset).append(s.charAt(i));
            }
        }
        
        StringBuilder ret = new StringBuilder();
        for (StringBuilder sb : sbList) {
            ret.append(sb.toString());
        }
        return ret.toString();
    }
}