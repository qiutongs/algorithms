class Solution {
    public String addBinary(String a, String b) {
        if (a == null || b == null || a.isEmpty() || b.isEmpty()) {
            return "";
        }
        int[] aArr = toIntArray(a);
        int[] bArr = toIntArray(b);
        int[] shortArr = aArr.length < bArr.length ? aArr : bArr;
        int[] longArr = aArr == shortArr ? bArr : aArr;
        
        List<Integer> ret = new ArrayList<>(longArr.length + 1);
        int carry = 0;
        for (int i = 0; i < longArr.length; i++) {
            int sum = i < shortArr.length ? shortArr[i] + longArr[i] + carry : longArr[i] + carry;
            ret.add(sum % 2);
            carry = sum / 2;
        }
        if (carry > 0) {
            ret.add(carry);
        }
        return toString(ret);
    }
    
    private int[] toIntArray(String s) {
        int l = s.length();
        int[] ret = new int[l];
        for (int i = 0; i < l; i++) {
            ret[i] = (int)(s.charAt(l - i - 1) - '0');
        }
        return ret;
    }
    
    private String toString(List<Integer> list) {
        int l = list.size();
        char[] ret = new char[l];
        for (int i = 0; i < l; i++) {
            ret[i] = (char)(list.get(l - i - 1) + '0');
        }
        return new String(ret);
    }
}