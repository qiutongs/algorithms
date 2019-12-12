class Solution {
    public int calPoints(String[] ops) {
        if (ops == null || ops.length == 0) {
            return 0;
        }
        List<Integer> points = new ArrayList<>();
        int ret = 0;
        for (int i = 0; i < ops.length; i++) {
            int cur = 0;
            switch(ops[i]) {
                case "+": 
                    cur = points.get(points.size() - 1) + points.get(points.size() - 2);
                    points.add(cur);
                    ret += cur;
                    break;
                case "D": 
                    cur = points.get(points.size() - 1) * 2;
                    points.add(cur);
                    ret += cur;
                    break;
                case "C":
                    ret -= points.get(points.size() - 1);
                    points.remove(points.size() - 1);
                    break;
                default: 
                    cur = Integer.valueOf(ops[i]);
                    points.add(cur);
                    ret += cur;
            }
        }
        return ret;
    }
}