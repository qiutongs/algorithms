// Forward declaration of the read4 API.
int read4(char *buf);

class Solution {
private:
    char tmp[4];
    int readIdx = 0;
    int tmpSize = 0;

public:
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    int read(char *buf, int n) {
        int index = 0;
        while(index < n) {
            if (readIdx == tmpSize) {
                tmpSize = read4(tmp);
                readIdx = 0;
            }
            for (; index < n && readIdx < tmpSize; index++, readIdx++) {
                buf[index] = tmp[readIdx];
            }
            if (tmpSize < 4) {
                break;
            }
        }
        return index;
    }
};

// Forward declaration of the read4 API.
int read4(char *buf);

class Solution {
private:
    char tmp[4];
    int readIdx = 0;
    int tmpSize = 0;

public:
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    int read(char *buf, int n) {
        int index = 0;
        while(index < n) {
            if (tmpSize == 0) {
                tmpSize = read4(tmp);
                readIdx = 0;
                if (tmpSize == 0) {
                    break;
                }
            }
            buf[index++] = tmp[readIdx++];
            tmpSize--;
        }
        return index;
    }
};