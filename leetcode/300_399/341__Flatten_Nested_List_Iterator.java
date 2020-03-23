/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    private Stack<Iterator<NestedInteger>> iterStack = new Stack<>();
    private Integer nextInt = null;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        iterStack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInt;
    }

    @Override
    public boolean hasNext() {
        while(iterStack.isEmpty() == false) {
            if (iterStack.peek().hasNext()) {
                NestedInteger next = iterStack.peek().next();
                if (next.isInteger()) {
                    nextInt = next.getInteger();
                    return true;
                } else {
                    iterStack.push(next.getList().iterator());
                }
            } else {
                iterStack.pop();
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */