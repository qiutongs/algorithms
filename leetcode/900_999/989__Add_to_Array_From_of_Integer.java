class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        if (A == null || A.length == 0) {
            return Collections.emptyList();
        }
        Deque<Integer> ret = new LinkedList<>();
        int carry = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            int sum = A[i] + K % 10 + carry;
            ret.addFirst(sum % 10);
            carry = sum / 10;
            K /= 10;
        }
        while(K > 0) {
            int sum = K % 10 + carry;
            ret.addFirst(sum % 10);
            carry = sum / 10;
            K /= 10;
        }
        if (carry > 0) {
            ret.addFirst(carry);
        }
        return (List<Integer>)ret;
    }
}