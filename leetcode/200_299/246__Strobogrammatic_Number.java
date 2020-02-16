class Solution {
    public boolean isStrobogrammatic(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (isValid(num.charAt(i)) == false) {
                return false;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        sb.reverse();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '6') {
                sb.setCharAt(i, '9');
            } else if (sb.charAt(i) == '9') {
                sb.setCharAt(i, '6');
            }
        }
        return sb.toString().equals(num);
    }
    
    private boolean isValid(char c) {
        int digit = c - '0';
        return digit == 0 || digit == 1 || digit == 8 || digit == 6 || digit == 9;
    }
}