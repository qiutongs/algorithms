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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        findRightSide(result, root, 0);
        return result;
    }
    
    private void findRightSide(List<Integer> result, TreeNode cur, int level){
        if (cur == null) return;
        
        if (level == result.size()){
            result.add(cur.val);
        }
        
        findRightSide(result, cur.right, level + 1);
        findRightSide(result, cur.left, level + 1);
    }
}