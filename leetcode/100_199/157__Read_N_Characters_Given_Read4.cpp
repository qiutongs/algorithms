// Forward declaration of the read4 API.
int read4(char *buf);

class Solution {
public:
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    int read(char *buf, int n) {
        char tmp[4];
        int i = 0;
        while(i < n) {
            int size = read4(tmp);
            for (int j = 0; i < n && j < size; i++, j++) {
                buf[i] = tmp[j];
            }
            if (size < 4) {
                break;
            }
        }
        return i;
    }
};