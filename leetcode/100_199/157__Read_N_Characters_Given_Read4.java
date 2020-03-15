/*
Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.
*/


/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf);
 */
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        if (buf == null || buf.length == 0 || n <= 0) {
            return 0;
        }
        if (n > buf.length) {
            n = buf.length;
        }
        
        int index = 0;
        
        char[] tmp = new char[4];
        int size = 0;
        int readIdx = 0;
        
        while(index < n) {
            if (readIdx == size) {
                size = read4(tmp);
                readIdx = 0;
                if (size == 0) {
                    break;
                }
            }
            buf[index++] = tmp[readIdx++];
        }
        return index;
    }
}

/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf);
 */
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        if (buf == null || buf.length == 0 || n <= 0) {
            return 0;
        }
        if (n > buf.length) {
            n = buf.length;
        }
        
        int index = 0;
        char[] tmp = new char[4];
        while(index < n) {
            int size = read4(tmp);
            for (int i = 0; i < size && index < n; i++, index++) {
                buf[index] = tmp[i];
            }
            if (size < 4) {
                break;
            }
        }
        return index;
    }
}