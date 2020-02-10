class Solution {
    public String multiply(String num1, String num2) {
        List<Integer> ret = new ArrayList<>();
        int[] n1 = toIntArray(num1);
        int[] n2 = toIntArray(num2);
        
        for (int i = 0; i < n2.length; i++) {
            int[] product = multiply(n1, n2[i]);
            addToRet(ret, product, i);
        }
        
        while(ret.size() > 1 && ret.get(ret.size() - 1) == 0) {
            ret.remove(ret.size() - 1);
        }

        return toString(ret);
    }
    
    private int[] multiply(int[] num, int d) {
        int[] ret = new int[num.length + 1];
        int carry = 0;
        for (int i = 0; i < num.length; i++) {
            int product = num[i] * d + carry;
            ret[i] = product % 10;
            carry = product / 10;
        }
        if (carry > 0) {
            ret[ret.length - 1] = carry;
        }
        return ret;
    }
    
    private void addToRet(List<Integer> ret, int[] product, int index) {
        int carry = 0;
        for (int i = index, j = 0; j < product.length; i++, j++) {
            int sum = 0;
            if (i < ret.size()) {
                sum = ret.get(i) + product[j] + carry;
                ret.set(i, sum % 10);
            } else {
                sum = product[j] + carry;
                ret.add(sum % 10);
            }
            carry = sum / 10;
        }
        if (carry > 0) {
            ret.add(carry);
        }
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