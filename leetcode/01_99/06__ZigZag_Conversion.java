class Solution1 {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return s;
        }
        if (numRows <= 0) {
            return null;
        }
        List<StringBuilder> sbList = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            sbList.add(new StringBuilder());
        }
        
        int groupSize = numRows == 1 ? 1 : numRows * 2 - 2;
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

class Solution2 {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return s;
        }
        if (numRows <= 0) {
            return null;
        }
        List<StringBuilder> sbList = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            sbList.add(new StringBuilder());
        }
        
        int groupSize = numRows == 1 ? 1 : numRows * 2 - 2;
        int groupNum = (s.length() + groupSize - 1) / groupSize;
        for (int i = 0; i < groupNum; i++) {
            for (int j = 0; j < groupSize; j++) {
                int index = i * groupSize + j;
                if (index >= s.length()) {
                    break;
                }
                if (j < numRows) {
                    sbList.get(j).append(s.charAt(index));
                } else {
                    sbList.get(groupSize - j).append(s.charAt(index));
                }
            }
        } 
        
        StringBuilder ret = new StringBuilder();
        for (StringBuilder sb : sbList) {
            ret.append(sb.toString());
        }
        return ret.toString();
    }
}