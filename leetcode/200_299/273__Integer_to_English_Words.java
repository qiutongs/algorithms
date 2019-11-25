class Solution {
    private final String[] LESS_THAN_20 = {
         "", "One", "Two", "Three", "Four", "Five",
         "Six", "Seven", "Eight", "Nine", "Ten",
         "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
         "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };
    private final String[] TENS = {
        "", "Ten", "Twenty", "Thirty", "Forty", "Fifty",
        "Sixty", "Seventy", "Eighty", "Ninety"
    };
    private final String[] BIG_NUMBERS = {"", "Thousand", "Million", "Billion"};
    
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        
        String ret = "";
        for (int i = 0; i < BIG_NUMBERS.length && num > 0; i++) {
            if ((num % 1000) > 0) {
                ret = concat(concat(toString3(num % 1000), BIG_NUMBERS[i]), ret);
            }
            num /= 1000;
        }
        return ret;
    }
    // num >= 0
    private String toString3(int num) {
        if (num < 100) {
            return toString2(num);
        } else {
            return concat(LESS_THAN_20[num / 100] + " Hundred", toString2(num % 100));
        }
    }
    // num >= 0
    private String toString2(int num) {
        String ret = null;
        if (num < 20) {
            ret = LESS_THAN_20[num];
        } else {
            ret = concat(TENS[num / 10], LESS_THAN_20[num % 10]);
        }
        return ret;
    }

    private String concat(String s1, String s2) {
        return s2.isEmpty() ? s1 : s1 + " " + s2;
    }
}