/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private HashMap<Integer, Integer> vfMap = new HashMap<>();
    
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            int[] tmp = {};
            return tmp;
        }
        getSum(root);
        
        int maxFre = 0;
        int count = 0;
        for (Integer fre : vfMap.values()) {
            if (fre > maxFre) {
                maxFre = fre;
                count = 1;
            } else if (fre == maxFre) {
                count++;
            }
        }
        
        int[] ret = new int[count];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : vfMap.entrySet()) {
            if (entry.getValue().intValue() == maxFre) {
                ret[i++] = entry.getKey();
            }
        }
        return ret;
    }
    
    private int getSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int ret = getSum(node.left) + getSum(node.right) + node.val;
        vfMap.compute(ret, (k, v) -> v == null ? 1 : v + 1);
        return ret;
    }
}