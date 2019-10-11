class Solution {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            List<Integer> ret = new ArrayList<>(1);
            ret.add(1);
            return ret;
        }
        
        List<Integer> newRow = new ArrayList<>(rowIndex);
        List<Integer> subRow = getRow(rowIndex - 1);
        
        newRow.add(1);
        
        for (int i = 0; i < subRow.size() - 1; i++) {
            newRow.add(subRow.get(i) + subRow.get(i + 1));
        }
        
        newRow.add(1);
        
        return newRow;
    }
}