/*
People usually think that a number that only contains 3 or 5 is a lucky number.

Now given an integer n, and you need to find the smallest lucky number that is not less than N and the number of occurrences of 3 in this lucky number is the same as the number of occurrences of 5.

Example
Example 1:

Input: n = "3500"
Output: "3535"
Explanation: 
  The smallest lucky number is 3533, but the number of occurrences of 3 is not equal to the number of occurrences of 5.
  The second smallest lucky number is 3535, and the number of occurrences of 3 is equal to the number of occurrences of 5.
Example 2:

Input: n = "1"
Output: "35"
Notice
1 ≤ n ≤ 10^100000
 */
public class Solution {
    /**
     * @param n: the n
     * @return: the smallest lucky number  that is not less than n
     */
    public String luckyNumber(String n) {
        if (n == null || n.isEmpty()) {
            return null;
        }
        int len = n.length();
        char msDigit = n.charAt(0);
        if (len % 2 == 1) {
            return get35(len + 1);
        } else {
            String n35 = get35(len);
            String n53 = get53(len);
            if (n.compareTo(n35) <= 0) {
                return n35;
            } else if (n.compareTo(n53) > 0) {
                return get35(len + 2);
            } else {
                String[] ret = { null };
                dfs(n, new StringBuilder(), len / 2, len / 2, ret);
                return ret[0];
            }
        }
    }
    
    private boolean dfs(String n, StringBuilder sb, int numOf3, int numOf5, String[] ret) {
        String curNum = sb.toString();
        if (n.length() == curNum.length()) {
            if (n.compareTo(curNum) <= 0 && numOf3 == numOf5) {
                ret[0] = ret[0] == null || curNum.compareTo(ret[0]) < 0 ? curNum : ret[0];
                return true;
            }
            return false;
        }
        
        if (curNum.compareTo(n.substring(0, curNum.length())) < 0) {
            //System.out.println(curNum);
            //System.out.println(n.substring(0));
            return false;
        }
        boolean res = false;
        if (numOf3 > 0) {
            sb.append('3');
            res = dfs(n, sb, numOf3 - 1, numOf5, ret);
            sb.deleteCharAt(sb.length() - 1);
    
            if (res) {
                return true;
            }
        }

        if (numOf5 > 0) {
            sb.append('5');
            res = dfs(n, sb, numOf3, numOf5 - 1, ret);
            sb.deleteCharAt(sb.length() - 1);
        }
        return res;
    }
    
    private String get35(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len / 2; i++) {
            sb.append('3');
        }
        for (int i = 0; i < len / 2; i++) {
            sb.append('5');
        }
        return sb.toString();
    }
    
    private String get53(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len / 2; i++) {
            sb.append('5');
        }
        for (int i = 0; i < len / 2; i++) {
            sb.append('3');
        }
        return sb.toString();
    }
}