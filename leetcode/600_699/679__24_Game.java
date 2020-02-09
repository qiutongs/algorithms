class Solution {
    public boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Set<Value> set = new HashSet<>();
        for (int num : nums) {
            set.add(new Value(num));
        }
        return dfs(set);
    }
    
    private boolean dfs(Set<Value> set) {
        if (set.size() == 1) {
            Value val = set.iterator().next();
            return val.divisible() && val.get() == 24;
        }
        List<Value> list = new ArrayList<>(set);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Value v1 = list.get(i);
                Value v2 = list.get(j);
                set.remove(v1);
                set.remove(v2);
                for (Value newVal : eval(v1, v2)) {
                    set.add(newVal);
                    if (dfs(set)) {
                        return true;
                    }
                    set.remove(newVal);  
                }
                set.add(v1);
                set.add(v2);
            }
        }
        return false;
    }
    
    private List<Value> eval(Value v1, Value v2) {
        List<Value> ret = new ArrayList<>();
        ret.add(v1.add(v2));
        ret.add(v1.substract(v2));
        ret.add(v2.substract(v1));
        ret.add(v1.multiply(v2));
        if (v2.numer != 0) {
            ret.add(v1.divide(v2));
        }
        if (v1.numer != 0) {
            ret.add(v2.divide(v1));
        }
        return ret;
    }
    
    private class Value {
        int numer;
        int denomi;
        Value(int n) {
            this.numer = n;
            this.denomi = 1;
        }
        Value(int numer, int denomi) {
            this.numer = numer;
            this.denomi = denomi;
        }
        boolean divisible() {
            return (numer % denomi) == 0;
        }
        int get() {
            return numer / denomi;
        }
        Value add(Value other) {
            return new Value(this.numer * other.denomi + this.denomi * other.numer, this.denomi * other.denomi);
        }
        Value substract(Value other) {
            return new Value(this.numer * other.denomi - this.denomi * other.numer, this.denomi * other.denomi);
        }
        Value multiply(Value other) {
            return new Value(this.numer * other.numer, this.denomi * other.denomi);
        }
        Value divide(Value other) {
            return new Value(this.numer * other.denomi, this.denomi * other.numer);
        }
    }
}