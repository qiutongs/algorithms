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
        
        Deque<String> ret = new LinkedList<>();
        for (int i = 0; i < BIG_NUMBERS.length && num > 0; i++, num /= 1000) {
            // if 0, don't add any unit
            if ((num % 1000) > 0) {
                // 1. add unit
                ret.addFirst(BIG_NUMBERS[i]);

                // 2. add 3 digits
                Deque<String> threeDigits = toString3(num % 1000);
                while(threeDigits.isEmpty() == false) {
                    ret.addFirst(threeDigits.removeLast());
                }
            }
        }
        return toString(ret);
    }

    private Deque<String> toString3(int num) {
        Deque<String> ret = toString2(num % 100);
        if (num >= 100) {
            ret.addFirst("Hundred");
            ret.addFirst(LESS_THAN_20[num / 100]);
        }
        return ret;
    }

    private Deque<String> toString2(int num) {
        Deque<String> ret = new LinkedList<>();
        if (num < 20) {
            ret.addLast(LESS_THAN_20[num]);
        } else {
            ret.addLast(TENS[num / 10]);
            ret.addLast(LESS_THAN_20[num % 10]);
        }
        return ret;
    }
    
    private String toString(Deque<String> queue) {
        StringBuilder sb = new StringBuilder();
        for (String str : queue) {
            if (str.isEmpty() == false) {
                sb.append(str);
                sb.append(' ');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}