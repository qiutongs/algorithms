// Time limit exceed
// TODO:
// 1. memorize the sub-result for a cake(x1, y1, x2, y2)
// 2. count cake more efficiently
// http://www.ishenping.com/ArtInfo/405594.html
public class Solution {
    /**
     * @param n: the length of the cake
     * @param m: the width of the cake
     * @param k: the number of the strewberries
     * @param mp: the position of the k strewberries
     * @return: the shortest cut length
     */
    public int getTheShortestCutLength(int n, int m, int k, int[][] mp) {
        int[] ret = { Integer.MAX_VALUE };
        HashSet<Integer> straws = new HashSet<>();
        for (int[] s : mp) {
            straws.add(s[0] * m + s[1]);
        }
        Deque<int[]> cakes = new LinkedList<>();
        cakes.add(new int[] {1, 1, n, m});
        dfs(m, cakes, straws, 0, ret);
        return ret[0];
    }
    
    private void dfs(int m, Deque<int[]> cakes, HashSet<Integer> straws, int cutLen, int[] ret) {
        if (cakes.isEmpty()) {
            ret[0] = Math.min(ret[0], cutLen);
            return;
        }
        int[] cake = cakes.poll();
        for (int i = 0; cake[1] + i < cake[3]; i++) {
            int[] cake1 = { cake[0], cake[1], cake[2], cake[1] + i };
            int[] cake2 = { cake[0], cake[1] + i + 1, cake[2], cake[3] };
            int straw1 = countStraws(m, cake1, straws);
            int straw2 = countStraws(m, cake2, straws);
            if (straw1 >= 1 && straw2 >= 1) {
                if (straw1 > 1) {
                    cakes.offer(cake1);
                }
                if (straw2 > 1) {
                    cakes.offer(cake2);
                }
                dfs(m, cakes, straws, cutLen + cake[2] - cake[0] + 1, ret);
                if (straw2 > 1) {
                    cakes.removeLast();
                }
                if (straw1 > 1) {
                    cakes.removeLast();
                }
            }
        }
        for (int i = 0; cake[0] + i < cake[2]; i++) {
            int[] cake1 = { cake[0], cake[1], cake[0] + i, cake[3] };
            int[] cake2 = { cake[0] + i + 1, cake[1], cake[2], cake[3] };
            int straw1 = countStraws(m, cake1, straws);
            int straw2 = countStraws(m, cake2, straws);
            if (straw1 >= 1 && straw2 >= 1) {
                if (straw1 > 1) {
                    cakes.offer(cake1);
                }
                if (straw2 > 1) {
                    cakes.offer(cake2);
                }
                dfs(m, cakes, straws, cutLen + cake[3] - cake[1] + 1, ret);
                if (straw2 > 1) {
                    cakes.removeLast();
                }
                if (straw1 > 1) {
                    cakes.removeLast();
                }
            }
        }
        cakes.addFirst(cake);
    }
    
    private int countStraws(int m, int[] cake, HashSet<Integer> straws) {
        int ret = 0;
        for (int i = cake[0]; i <= cake[2]; i++) {
            for (int j = cake[1]; j <= cake[3]; j++) {
                if (straws.contains(i * m + j)) {
                    ret++;
                }
            }
        }
        return ret;
    }
}