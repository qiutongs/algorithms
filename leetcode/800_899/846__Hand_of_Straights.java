class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        HashMap<Integer, Integer> vfMap = new HashMap<>();
        for (int card : hand) {
            vfMap.compute(card, (k,v) -> v == null ? 1 : v + 1);
        }
        
        Arrays.sort(hand);
        
        for (int card : hand) {
            if (vfMap.get(card) == 0) {
                continue;
            }
            for (int i = 0; i < W; i++) {
                if (vfMap.containsKey(card + i) && vfMap.get(card + i) > 0) {
                    vfMap.compute(card + i, (k,v) -> v - 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}