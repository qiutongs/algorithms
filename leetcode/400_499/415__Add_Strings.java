class Solution {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
            return "";
        }
        int[] arr1 = toIntArray(num1);
        int[] arr2 = toIntArray(num2);
        
        List<Integer> ret = new ArrayList<>(Math.max(arr1.length, arr2.length) + 1);
        int carry = 0;
        for (int i = 0; i < Math.max(arr1.length, arr2.length); i++) {
            int sum = carry;
            sum = i < arr1.length ? sum + arr1[i] : sum;
            sum = i < arr2.length ? sum + arr2[i] : sum;
            ret.add(sum % 10);
            carry = sum / 10;
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
        for (int i = 0; i < list.size(); i++) {
            ret[i] = (char)(list.get(l - i - 1) + '0');
        }
        return new String(ret);
    }
}