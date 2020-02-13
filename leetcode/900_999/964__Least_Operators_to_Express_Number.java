// Raw BFS
// Time Limit Exceeded: 3, 365
class Solution {
    public int leastOpsExpressTarget(int x, int target) {
        if (x == target) {
            return 0;
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, x});
        
        int ret = 0;
        while(q.isEmpty() == false) {
            ret++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] nb : eval(cur, x)) {
                    if (nb[0] == target) {
                        return ret;
                    }
                    q.offer(nb);
                }
            }
        }
        return -1;
    }
                
    private List<int[]> eval(int[] cur, int x) {
        List<int[]> ret = new ArrayList<>();
        int val = cur[0], mul = cur[1];
        ret.add(new int[]{val + x, x});
        ret.add(new int[]{val - x, -x});
        ret.add(new int[]{val - mul + mul * x, mul * x});
        if (mul % x == 0) {
            ret.add(new int[]{val - mul + mul / x, mul / x});
        }
        return ret;
    }
}