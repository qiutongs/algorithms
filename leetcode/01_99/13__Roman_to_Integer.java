class Solution {
    private static HashMap<Character, Integer> SYMBOLS = new HashMap<>();
    private static HashMap<Character, Character> SUB = new HashMap<>();
    
    static {
        SYMBOLS.put('I', 1);
        SYMBOLS.put('V', 5);
        SYMBOLS.put('X', 10);
        SYMBOLS.put('L', 50);
        SYMBOLS.put('C', 100);
        SYMBOLS.put('D', 500);
        SYMBOLS.put('M', 1000);
        
        SUB.put('X', 'I');
        SUB.put('V', 'I');
        SUB.put('L', 'X');
        SUB.put('C', 'X');
        SUB.put('D', 'C');
        SUB.put('M', 'C');
    }
    
    public int romanToInt(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int ret = 0;
        char prev = '\0';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Character sub = SUB.get(c);
            if (sub != null && sub.charValue() == prev) {
                ret += SYMBOLS.get(c) - 2 * SYMBOLS.get(prev);
            } else {
                ret += SYMBOLS.get(c);
            }
            prev = c;
        }
        return ret;
    }
}