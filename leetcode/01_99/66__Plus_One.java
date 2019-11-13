class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = i == digits.length - 1 ? digits[i] + 1 + carry : digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
            if (carry == 0) {
                break;
            }
        }
        if (carry > 0) {
            int[] ret = new int[digits.length + 1];
            System.arraycopy(digits, 0, ret, 1, digits.length);
            ret[0] = carry;
            digits = ret;
        }
        return digits;
    }
}