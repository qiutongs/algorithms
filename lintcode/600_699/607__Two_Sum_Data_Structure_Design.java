public class TwoSum {
    private final HashMap<Integer, Integer> map = new HashMap<>();
    /**
     * @param number: An integer
     * @return: nothing
     */
    public void add(int number) {
        map.compute(number, (k, v) -> v == null ? 1 : v + 1);
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            Integer first = entry.getKey();
            Integer firstV = entry.getValue();
            Integer secondV = map.get(value - first);
            if (secondV != null) {
                if (first != value - first) {
                    return true;
                }
                
                if (firstV > 1) {
                    return true;
                }
            }
        }
        return false;
    }
}