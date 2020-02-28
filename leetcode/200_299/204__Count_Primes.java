// Similar idea as Sieve_of_Eratosthenes but also compute composite
// Time: O(N * N)
// Space: O(N)
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        int ret = n - 2;
        boolean[] composite = new boolean[n + 1];
        for (int i = 2; i * i < n; i++) {
            for (int j = i; j * i < n; j++) {
                if (composite[i * j] == false) {
                    composite[i * j] = true;
                    ret--;
                }
            }
        }
        return ret;
    }
}

// Sieve_of_Eratosthenes
// Time: O(NloglogN)
// Space: O(N)
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        int ret = n - 2;
        boolean[] composite = new boolean[n + 1];
        for (int i = 2; i * i < n; i++) {
            if (composite[i]) {
                continue;
            }
            for (int j = i; j * i < n; j++) {
                if (composite[i * j] == false) {
                    composite[i * j] = true;
                    ret--;
                }
            }
        }
        return ret;
    }
}