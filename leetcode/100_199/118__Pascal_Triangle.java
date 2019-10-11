class Solution {
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> ret = new ArrayList<>(numRows);
        
        if (numRows == 1) {
            ret.add(Arrays.asList(1));
            return ret;
        }
        
        List<List<Integer>> subRet = generate(numRows - 1);
        
        List<Integer> newRow = new ArrayList<>(numRows);
        newRow.add(1);
        
        List<Integer> lastRow = subRet.get(subRet.size() - 1);
        for (int i = 0; i < lastRow.size() - 1; i++) {
            newRow.add(lastRow.get(i) + lastRow.get(i + 1));
        }
        
        newRow.add(1);
        
        subRet.add(newRow);
        
        return subRet;
    }
}