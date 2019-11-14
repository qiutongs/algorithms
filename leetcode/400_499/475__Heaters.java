class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int ret = 0;
        for (int i = 0; i < houses.length; i++) {
            int index = Arrays.binarySearch(heaters, houses[i]);
            index = index < 0 ? -index - 1 : index;
            
            int rightDis = index == heaters.length ? Integer.MAX_VALUE : heaters[index] - houses[i];
            int leftDis = index - 1 >= 0 ? houses[i] - heaters[index - 1] : Integer.MAX_VALUE;
            ret = Math.max(ret, Math.min(leftDis, rightDis));
        }
        return ret;
    }
}