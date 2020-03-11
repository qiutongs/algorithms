public class Solution {
    public int updateBits(int n, int m, int i, int j) {
        // 000111...111000
        int mask = 0xFFFFFFFF >>> (32 - (j - i + 1));
        mask <<= i;
        // n -> XXX000..000XXX -> XXX_m_bits_XXX
        return n & ~mask | (m << i);
    }
}