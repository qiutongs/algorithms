class RandomizedSet {
    private HashMap<Integer, Integer> viMap = new HashMap<>();
    private ArrayList<Integer> valList = new ArrayList<>();
    private Random rand = new Random();
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (viMap.containsKey(val)) {
            return false;
        } else {
            valList.add(val);
            viMap.put(val, valList.size() - 1);
            return true;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (viMap.containsKey(val)) {
            int index = viMap.get(val);
            int tmpVal = valList.get(index);
            valList.set(index, valList.get(valList.size() - 1));
            valList.remove(valList.size() - 1);
            
            if (index < valList.size()) {
                viMap.put(valList.get(index), index);
            }
            viMap.remove(val);
            return true;
        } else {
            return false;
        }
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return valList.get(rand.nextInt(valList.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */