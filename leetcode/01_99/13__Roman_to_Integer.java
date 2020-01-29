class Solution {
    private static HashMap<Character, Integer> SYMBOLS= new HashMap<>();
    
    static {
        SYMBOLS.put('I', 1);
        SYMBOLS.put('V', 5);
        SYMBOLS.put('X', 10);
        SYMBOLS.put('L', 50);
        SYMBOLS.put('C', 100);
        SYMBOLS.put('D', 500);
        SYMBOLS.put('M', 1000);
    }
    
    public int romanToInt(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int ret = 0;
        char prev = '\0';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            ret += eval(prev, c);
            prev = c;
        }
        return ret;
    }
    
    private int eval(char prev, char c) {
        if ((prev == 'I' && (c == 'V' || c == 'X'))
         || (prev == 'X' && (c == 'L' || c == 'C'))
         || (prev == 'C' && (c == 'D' || c == 'M'))) {
            return SYMBOLS.get(c) - 2 * SYMBOLS.get(prev);
        } else {
            return SYMBOLS.get(c);
        }
    }
}