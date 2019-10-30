/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// If balanced tree, T(n) = 2 * T(n/2) + O(n) -> T(n) = nlogn
// if not (say a list), T(n) = T(n - 1) + O(n) -> T(n) = n^2
class Solution1 {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        
        return pathSum(root.left, sum) + pathSum(root.right, sum) + pathSumStartHere(root, sum);
    }
    
    private int pathSumStartHere(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        
        int ret = node.val == sum ? 1 : 0;

        ret += pathSumStartHere(node.left, sum - node.val)
             + pathSumStartHere(node.right, sum - node.val);
        
        return ret;
    }
}

// prefix sum: T(n) = O(n)
class Solution2 {
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> prefixSums = new HashMap<>();
        prefixSums.put(0, 1);
        return pathSum(root, 0, sum, prefixSums);
    }
    
    private int pathSum(TreeNode node, int curSum, int target, HashMap<Integer, Integer> prefixSums) {
        if (node == null) {
            return 0;
        }

        curSum += node.val;
        Integer count = prefixSums.get(curSum - target);
        int ret = count == null ? 0 : count;
        
        prefixSums.compute(curSum, (k,v) -> v == null ? 1 : v + 1);
        ret += pathSum(node.left, curSum, target, prefixSums);
        ret += pathSum(node.right, curSum, target, prefixSums);
        prefixSums.compute(curSum, (k,v) -> v - 1);
        
        return ret;
    }
}