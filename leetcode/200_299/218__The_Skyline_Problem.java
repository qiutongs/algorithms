class Solution {
    public List<List<Integer>> getSkyline(int[][] inputs) {
        List<Building> buildings1 = new ArrayList<>();
        for (int[] input : inputs) {
            buildings1.add(new Building(input[0], input[1], input[2]));
        }
        List<Building> buildings2 = new ArrayList<>(buildings1);
        Collections.sort(buildings2, (b1, b2) -> b1.end - b2.end);
        
        TreeSet<Building> bst = new TreeSet<>();
        
        List<List<Integer>> ret = new ArrayList<>();
        int i = 0, j = 0;
        while(i < buildings1.size() || j < buildings2.size()) {
            if (i < buildings1.size() && buildings1.get(i).start <= buildings2.get(j).end) {
                int time = buildings1.get(i).start;
                while(i < buildings1.size() && buildings1.get(i).start == time) {
                    bst.add(buildings1.get(i));
                    i++;
                }
                int maxH = bst.last().h;
                addToRet(ret, time, maxH);
            } else {
                int time = buildings2.get(j).end;
                while(j < buildings2.size() && buildings2.get(j).end == time) {
                    bst.remove(buildings2.get(j));
                    j++;
                }
                int maxH = bst.isEmpty() ? 0 : bst.last().h;
                addToRet(ret, time, maxH);
            }
        }
        return ret;
    }
    
    private void addToRet(List<List<Integer>> ret, int start, int h) {
        if (ret.isEmpty() || h != ret.get(ret.size() - 1).get(1)) {
            ret.add(Arrays.asList(start, h));
        }
    }
    
    private class Building implements Comparable<Building> {
        int start;
        int end;
        int h;
        Building(int start, int end, int h) {
            this.start = start;
            this.end = end;
            this.h = h;
        }
        public int compareTo(Building other) {
            return this.h != other.h ? this.h - other.h : this.start - other.start;
        }
        public boolean equals(Object other) {
            return compareTo((Building)other) == 0;
        }
    }
}