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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recurLargestValues(result, root, 0);
        return result;
    }
    
    private void recurLargestValues(List<Integer> result, TreeNode cur, int level){
        if (cur == null) return;
        
        if (level >= result.size()){
            result.add(cur.val);
        } else {
            int curVal = result.get(level);
            result.set(level, curVal > cur.val? curVal : cur.val );
        }
        
        recurLargestValues(result, cur.left, level + 1);
        recurLargestValues(result, cur.right, level + 1);
    }
}