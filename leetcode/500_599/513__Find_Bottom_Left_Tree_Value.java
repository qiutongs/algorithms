/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class RowInfo{
    int val;
    int level;
    RowInfo(int v, int l){val = v; level = l;}
}
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        return findBottomLeftValue(root, 0).val;
    }
    
    private RowInfo findBottomLeftValue(TreeNode cur, int level){
        if (cur == null) return null;
        
        RowInfo leftRowInfo = findBottomLeftValue(cur.left, level+1);
        RowInfo rightRowInfo = findBottomLeftValue(cur.right, level+1);
        
        if (leftRowInfo == null && rightRowInfo == null){
            return new RowInfo(cur.val, level);
        } else if (leftRowInfo != null && rightRowInfo != null){
            if (leftRowInfo.level >= rightRowInfo.level){
                return leftRowInfo;
            } else {
                return rightRowInfo;
            }
        } else {
            return leftRowInfo == null? rightRowInfo : leftRowInfo;
        }
    }
}