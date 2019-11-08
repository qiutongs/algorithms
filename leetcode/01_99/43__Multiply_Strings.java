class Solution2 {
    public String multiply(String num1, String num2) {
        List<Character> ret = new ArrayList<>();
        num1 = reverse(num1);
        num2 = reverse(num2);
        
        for (int i = 0; i < num2.length(); i++) {
            List<Character> product = multiply(num1, num2.charAt(i));
            addToRet(ret, product, i);
        }
        
        while(ret.size() > 1 && ret.get(ret.size() - 1) == '0') {
            ret.remove(ret.size() - 1);
        }
        
        char[] charArr = new char[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
            charArr[i] = ret.get(ret.size() - i - 1);
        }
        return new String(charArr);
    }
    
    private List<Character> multiply(String num1, char c) {
        List<Character> ret = new ArrayList<>(num1.length() + 1);
        int carry = 0;
        for (int i = 0; i < num1.length(); i++) {
            int product = mult(num1.charAt(i), c) + carry;
            ret.add(toChar(product % 10));
            carry = product / 10;
        }
        if (carry > 0) {
            ret.add(toChar(carry));
        }
        return ret;
    }
    
    private void addToRet(List<Character> ret, List<Character> product, int index) {
        int carry = 0;
        int j = 0;
        for (int i = index; i < ret.size() && j < product.size(); i++, j++) {
            int sum = add(ret.get(i), product.get(j)) + carry;
            ret.set(i, toChar(sum % 10));
            carry = sum / 10;
        }
        while(j < product.size()) {
            int sum = toInt(product.get(j)) + carry;
            ret.add(toChar(sum % 10));
            carry = sum / 10;
            j++;
        }
        if (carry > 0) {
            ret.add(toChar(carry));
        }
    }
    
    private char toChar(int i) {
        return (char)(i + '0');
    }
    
    private int toInt(char c) {
        return (int)(c - '0');
    }
    
    private int add(char c1, char c2) {
        return toInt(c1) + toInt(c2);
    }
    
    private int mult(char c1, char c2) {
        return toInt(c1) * toInt(c2);
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.reverse();
        return sb.toString();
    }
}