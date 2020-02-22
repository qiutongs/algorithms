// Counter
public class Codec {
    private final HashMap<Integer, String> idUrlMap = new HashMap<>();
    private int id = 0;
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String ret = "http://tinyurl.com/" + id;
        idUrlMap.put(id, longUrl);
        id++;
        return ret;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String strId = shortUrl.substring(shortUrl.lastIndexOf('/') + 1, shortUrl.length());
        return idUrlMap.get(Integer.valueOf(strId));
    }
}