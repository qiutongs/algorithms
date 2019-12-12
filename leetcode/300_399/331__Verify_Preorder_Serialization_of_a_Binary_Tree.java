class Solution {
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.isEmpty()) {
            return false;
        }
        String[] tokens = preorder.split(",");
        int[] index = { 0 };
        return isValid(tokens, index) && index[0] == tokens.length - 1;
    }
    
    private boolean isValid(String[] tokens, int[] index) {
        if (index[0] == tokens.length) {
            return false;
        }
        if (tokens[index[0]].equals("#")) {
            return true;
        }
        index[0]++;
        if (isValid(tokens, index) == false) {
            return false;
        }
        index[0]++;
        return isValid(tokens, index);
    }
}