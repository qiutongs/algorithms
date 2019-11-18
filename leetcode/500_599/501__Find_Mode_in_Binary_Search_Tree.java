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
    public int[] findMode(TreeNode root) {
        if (root == null) {
            int[] empty = {};
            return empty;
        }
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        
        List<Integer> ret = new ArrayList<>();
        int max = 1, count = 1;
        ret.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).intValue() == list.get(i - 1).intValue()) {
                count++;
            } else {
                count = 1;
            }
            
            if (count >= max) {
                if (count > max) {
                    ret.clear();
                    max = count;
                }
                ret.add(list.get(i));
            }
        }
        return toArray(ret);
    }
    
    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
    
    private int[] toArray(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}

class Solution {
    private int maxFre = 0;
    private int curFre = 0;
    private List<Integer> list = new ArrayList<>();
    private Integer lastVal = null;
    
    public int[] findMode(TreeNode root) {
        int[] ret = new int[0];
        if (root == null) {
            return ret;
        }
        inorder(root);
        updateFre();
        ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
    
    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        if (lastVal == null) {
            lastVal = node.val;
            curFre = 1;
        } else {
            if (lastVal == node.val) {
                curFre++;
            } else {
                updateFre();
                lastVal = node.val;
                curFre = 1;
            }
        }
        inorder(node.right);
    }
    
    private void updateFre() {
        if (curFre >= maxFre) {
            if (curFre > maxFre) {
                list.clear();
                maxFre = curFre;
            }
            list.add(lastVal);
        }
    }
}