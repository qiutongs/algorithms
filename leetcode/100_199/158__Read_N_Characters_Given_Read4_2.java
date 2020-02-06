/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf); 
 */
public class Solution extends Reader4 {
    private char[] tmp = new char[4];
    private int readIdx = 0;
    private int size = 0;
    
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