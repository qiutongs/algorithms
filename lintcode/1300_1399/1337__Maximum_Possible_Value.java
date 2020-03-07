public class Solution {
    /**
     * @param N: an integer
     * @return: returns the maximum possible value obtained by inserting one '5' digit
     */
    public int MaximumPossibleValue(int N) {
        boolean positive = N >= 0;
        N = Math.abs(N);
        String strN = String.valueOf(N);
        
        if (positive) {
            for (int i = 0; i < strN.length(); i++) {
                if (strN.charAt(i) < '5') {
                    String ret = strN.substring(0, i) + '5' + strN.substring(i, strN.length());
                    return Integer.valueOf(ret);
                }
            }
            return Integer.valueOf(strN + '5');
        } else {
            for (int i = 0; i < strN.length(); i++) {
                if (strN.charAt(i) > '5') {
                    String ret = strN.substring(0, i) + '5' + strN.substring(i, strN.length());
                    return -Integer.valueOf(ret);
                }
            }
            return -Integer.valueOf(strN + '5');
        }
    }
}